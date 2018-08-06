#include <stdlib.h>
#include <nfc/nfc.h>
#include <string.h>
#include <signal.h>

#define MAX_DEVICE_COUNT 16
#define MAX_MESSAGE_LENGTH 50
#define MAX_TARGET_COUNT 16
#define LOOP_TIME 20

static void
print_hex(const uint8_t *pbtData, const size_t szBytes)
{
  size_t szPos;

  for (szPos = 0; szPos < szBytes; szPos++) {
    printf("%02x ", pbtData[szPos]);
  }
  printf("\n");
}

static char*
stringify_hex(const uint8_t *id, const size_t szBytes)
{
  int i;
  char *idstring;
  size_t szIdstring = 2*szBytes;
  idstring = (char *)malloc(szIdstring+1);
  for (i=0; i<szIdstring/2; i++){
    snprintf((idstring+i+i), szIdstring, "%02x", *(id+i));
  }
  printf("The ID string is:%s\n",idstring);
  return idstring;
}

int
main(int argc, const char*argv[])
{
/* initialize nfc variables */
nfc_device *pnd;
nfc_target nt;
nfc_context *context;

nfc_init(&context);
if (context==NULL) {
  printf("unable to init libnfc (malloc)\n");
  exit(EXIT_FAILURE);
}
  
/* display the libnfc version */
const char *acLibnfcVersion = nfc_version();
(void)argc;
printf("%s uses libnfc %s\n", argv[0], acLibnfcVersion);

/* initialize message arrays */
char message[MAX_MESSAGE_LENGTH*MAX_DEVICE_COUNT];
char oldMessage[MAX_MESSAGE_LENGTH*MAX_DEVICE_COUNT];
strcpy(oldMessage, "");

/* Keep looping */
while(1) { 
  /* List the number of nfc devices */
  nfc_connstring connstrings[MAX_DEVICE_COUNT]; 
  size_t szDeviceCount = nfc_list_devices(context, connstrings, MAX_DEVICE_COUNT);
  printf("Devices found:\n");
  int i;
  
  /* Reset the variable for the total message string */
  strcpy(message, "");

  /* Loop through the devices */
  for (i=0; i<szDeviceCount; i++) {
    printf("%s\n", connstrings[i]);
 
    /* Open nfc device */
    pnd = nfc_open(context, connstrings[i]);

    if (pnd == NULL) {
      printf("ERROR: %s\n", "Unable to open NFC Device.");
      exit(EXIT_FAILURE);
    }

    /* set it to initiator mode */
    if (nfc_initiator_init(pnd) < 0) {
      nfc_perror(pnd, "nfc_initiator_init");
      exit(EXIT_FAILURE);
    }
  
    printf("reader_name: %s\n", nfc_device_get_name(pnd));
    printf("reader_connstring: %s\n", nfc_device_get_connstring(pnd));

    /* poll for a MIFARE tag: */
    const nfc_modulation nmMifare = {
      .nmt = NMT_ISO14443A,
      .nbr = NBR_106,
    };

    char *nfcid;
    if (nfc_initiator_select_passive_target(pnd, nmMifare, NULL, 0, &nt) > 0) {
      printf("The following card was found:");
      printf("UID (NFCID%c): ", (nt.nti.nai.abtUid[0] == 0x08 ? '3': '1'));
     
      print_hex(nt.nti.nai.abtUid, nt.nti.nai.szUidLen);
      nfcid = (char *)malloc(nt.nti.nai.szUidLen*2+1);
      nfcid = stringify_hex(nt.nti.nai.abtUid, nt.nti.nai.szUidLen);
    }
    else {
      nfcid = "0000000000"; /* ten zeroes indicate no nfc tag */
    } 
  
    /* Create string with the device ID and the tag ID. 
       Assumes that there is only one tag at all times */
    if (nfcid) {
      char *buf;
      size_t szBuf;
      szBuf = snprintf(NULL, 0, "connstring=%s;nfcid=%s,",connstrings[i],nfcid);
      buf = (char *)malloc(szBuf+1);
      snprintf(buf, szBuf+1, "connstring=%s;nfcid=%s,",connstrings[i],nfcid); 
      printf("%s\n",buf);
      strcat(message, buf);
      printf("%s\n",message); 
    }
    else {
      printf("ERROR: nfcid variable is empty when it shouldn't be.\n");
    }
 
    /* close the NFC device */
    nfc_close(pnd);
  }
  
  /* Test if anything changed */
  if (strcmp(oldMessage, message) != 0) {  
    printf("Change detected: message: %s, oldMessage: %s\n", message, oldMessage);
    /* Call python script reportOverZmq.py */
    char *command;
    size_t szCommand;
    szCommand = snprintf(NULL, 0, "python reportOverZmq.py \" %s \" ", message);
    command = (char *)malloc(szCommand + 1);
    snprintf(command, szCommand, "python reportOverZmq.py \" %s \" ", message);
    printf("%s\n", command);
    int erCommand;
    erCommand = system(command);
    strncpy(oldMessage, message, MAX_MESSAGE_LENGTH*MAX_DEVICE_COUNT);
  }
  
  /* Delay */
  sleep(LOOP_TIME);
}  
}

