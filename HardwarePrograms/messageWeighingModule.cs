using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading;
using System.Net.Sockets;

namespace DFKI.OpcUa.HAP.I40
{

class MessagingClient
    {
        private object m_data_lock = new object();

        #region Constructor
        public MessagingClient (string host, int port)
        {
            if (host != null)
            {
                this.host = host;
            }
            else
            {
                this.host = "localhost";
            }

            if (port != 0)
            {
                this.port = port;
            }
            else
            {
                this.port = 9999;
            }

            this.senderName = "MessagingClient";

            try
            {
                client = new TcpClient(this.host, this.port);
                client.SendTimeout = 1000;
                client.ReceiveTimeout = 1000;
                error = "";
                new Thread(this.ListenMessageServer).Start();
            } catch (Exception e)
            {
                error = e.Message;
                Thread.Sleep(1000);
            }
        }

        public MessagingClient (string host, int port, string senderName)
        {
            if (host != null)
            {
                this.host = host;
            }
            else
            {
                this.host = "localhost";
            }

            if (port != 0)
            {
                this.port = port;
            }
            else
            {
                this.port = 9999;
            }

            if (senderName != null)
            {
                this.senderName = senderName;
            }

            try
            {
                client = new TcpClient(this.host, this.port);
                client.SendTimeout = 1000;
                client.ReceiveTimeout = 1000;
                error = "";
                new Thread(this.ListenMessageServer).Start();
            }
            catch (Exception e)
            {
                error = e.Message;
                Thread.Sleep(1000);
            }
        }

        #endregion

        #region public methods
        public void RequestStop()
        {
            _shouldStop = true;
        }

        public void ListenMessageServer()
        {
            if (client == null)
            {
                return;
            }
            if (stream == null)
            {
                stream = client.GetStream();
            }
            while (!_shouldStop)
            {
                try
                {
                    if (client.Available > 0)
                    {
                        lock (m_data_lock)
                        {
                            int bytes = stream.Read(incBuffer, 0, 1024);

                            string text = Encoding.ASCII.GetString(incBuffer, 0, bytes);

                            string[] msg = extractInfo(text);

                            if (msg[(int)MSG.Topic] != "" && msg.Length >= 3)
                            {
                                MessageReceivedEventArgs args = new MessageReceivedEventArgs();
                                args.Topic = msg[(int)MSG.Topic];
                                args.Property = msgProperties(msg[(int)MSG.Property]);
                                args.Sender = msg[(int)MSG.Sender];
                                onMessageReceived(args);
                            }
                        }
                    }
                } catch (SocketException e)
                {
                    //TODO Socket Exception
                    Console.WriteLine("SocketException in ListenMessagingServer of messaging client: " + e.Message);
                } catch (InvalidOperationException e)
                {
                    //TODO Invalid Operation (getStream)
                    Console.WriteLine("InvalidOperation in ListenMessagingServer of messaging client: " + e.Message);
                    stream.Close();
                    client.Close();
                    client.Connect(host, port);
                    stream = client.GetStream();
                }
            }
            stream.Close();
            client.Close();
        }

        public bool setMessage(string topic, Dictionary<string, string> property, string sender)
        {
            // TODO implement function
            if (outBuffer != null)
            {
                // How to handle multiple sends if an old one wasn't send so far???
            } else if (topic != "" && sender != "")
            {
                lock (m_data_lock)
                {
                    string properties = String.Empty;
                    if (property != null)
                    {
                        foreach (var key in property.Keys)
                        {
                            if (properties == "")
                            {
                                properties += String.Format("{0}:{1}", key, property[key]);
                            }
                            else
                            {
                                properties += String.Format(",{0}:{1}", key, property[key]);
                            }
                        }
                    }
                    string text = String.Format("<{0};{1};{2}>", topic, properties, sender);
                    outBuffer = Encoding.UTF8.GetBytes(text);
                }
                return true;
            }
            return false;
        }

        public bool setMessage(byte[] msg)
        {
            // TODO implement function
            if (outBuffer != null)
            {
                // How to handle multiple sends if an old one wasn't send so far???
            }
            else if (msg != null)
            {
                lock (m_data_lock)
                {
                    outBuffer = msg;
                    return true;
                }
            }
            return false;
        }

        public bool sendMessage()
        {
            if (stream == null)
            {
                stream = client.GetStream();
            }
            if (outBuffer != null)
            {
                lock (m_data_lock)
                {
                    try
                    {
                        stream.Write(outBuffer, 0, outBuffer.Length);
                        outBuffer = null;
                        return true;
                    } catch (Exception e)
                    {
                        Console.WriteLine("Exception in sendMessage of MessagingClient: " + e.Message);
                    }
                }
            }
            return false;
        }
        #endregion

        #region private methods
        private string[] extractInfo(string text)
        {
            if (text.StartsWith("<"))
            {
                text = text.Substring(1);
            }
            if (text.EndsWith(">"))
            {
                text = text.Substring(0, text.Length - 1);
            }

            string[] arr = text.Split(';');
            return arr;
        }

        private Dictionary<string, string> msgProperties(string v)
        {
            Dictionary<string, string> propertyList = new Dictionary<string, string>();
            if (v != "")
            {
                string[] properties = v.Split(',');

                foreach (string prop in properties)
                {
                    string[] keyValue = prop.Split(':');
                    propertyList.Add(keyValue[0], keyValue[1]);
                }
            }
            return propertyList;
        }
        #endregion

        #region protected methods
        protected virtual void onMessageReceived (MessageReceivedEventArgs args)
        {
            // MessageRecieved?.Invoke(this, args);
	    if (MessageRecieved != null) {
		MessageRecieved.Invoke(this, args);
	    }
        }
        #endregion


        #region fields
        private enum MSG : int { Topic, Property, Sender};

        public event EventHandler<MessageReceivedEventArgs> MessageRecieved;

        private TcpClient client;
        private NetworkStream stream = null;
        private string host;
        private int port;
        public string senderName;
        private volatile bool _shouldStop = false;
        private string error;
        private byte[] incBuffer = new byte[1024];
        private byte[] outBuffer = null;
        #endregion
    }

    public class MessageReceivedEventArgs : EventArgs
    {
        public string Topic { get; set; }
        public Dictionary<string, string> Property { get; set; }
        public string Sender { get; set; }
    }

class MessageBuilder
    {

        private MessagingClient client;

        public MessageBuilder(MessagingClient client)
        {
            this.client = client;
        }

        private byte[] GetMessage (string topic, string properties, string sender)
        {
            string text = String.Format("<{0};{1};{2}>", topic, properties, sender);
            return Encoding.UTF8.GetBytes(text);
        }

	    // Notification of a new recogniyed RFID tag with product name and weight (getting the message)
	    public byte[] ReportRfidTagAndInfo (uint id, string name, double weight, string sender)
	    {
	      string topic = "ReportRFIDTagAndInfo";
	      string property = String.Format("id:{0},name:{1},weight:{2}", id, name, weight);
	      return GetMessage(topic, property, sender);
	    }

	    // Notification of a new recognized RFID tag with product name and weight (sending the message via the stored client)
	    public void ReportRfidTagAndInfo (uint id, string name, double weight)
	    {
	        client.setMessage(ReportRfidTagAndInfo(id, name, weight, client.senderName));
	        client.sendMessage();
	    }

        // Notification of a new recognized RFID tag (getting the Message)
        public byte[] ReportRfidTag (uint id, string sender)
        {
            string topic = "ReportRFIDTag";
            string property = String.Format("value:{0}", id);
            return GetMessage(topic, property, sender);
        }

        // Notification of a new recognized RFID tag (sending the message via stored client)
        public void ReportRfidTag (uint id)
        {
            client.setMessage(ReportRfidTag(id, client.senderName));
            client.sendMessage();
        }

        // Notification of fetched Parts / componentes out of a specific box (boxPosition = left / right / middle)
        public byte[] ReportFetchedPart (string partId, int amount, string boxPosition, string sender)
        {
            string topic = "ReportFetchedPart";
            string property = String.Format("Part:{0},Amount:{1},Box:{2}", partId, amount, boxPosition);
            return GetMessage(topic, property, sender);
        }

        // Notification of fetched Parts / componentes out of a specific box (boxPosition = left / right / middle)
        public void ReportFetchedPart (string partId, int amount, string boxPosition)
        {
            client.setMessage(ReportFetchedPart(partId, amount, boxPosition, client.senderName));
            client.sendMessage();
        }

        // Notification of low filling level of a specific box with parts (boxPosition = left / right / middle)
        public byte[] ReportLowFilling (string partId, string boxPosition, string sender)
        {
            string topic = "ReportLowFilling";
            string property = String.Format("Part:{0},Box:{1}", partId, boxPosition);
            return GetMessage(topic, property, sender);
        }

        // Notification of low filling level of a specific box with parts (boxPosition = left / right / middle)
        public void ReportLowFilling (string partId, string boxPosition)
        {
            client.setMessage(ReportLowFilling(partId, boxPosition, client.senderName));
            client.sendMessage();
        }

        public byte[] SetDockingMode (byte dockingMode, string sender)
        {
            string topic = "SetDockingMode";
            string property = String.Format("value:{0}", dockingMode);
            return GetMessage(topic, property, sender);
        }

        public void SetDockingMode(byte dockingMode)
        {
            client.setMessage(SetDockingMode(dockingMode, client.senderName));
            client.sendMessage();
        }

        public byte[] CreateIncomingJob (uint customer, uint order, byte source, uint transportationId, string sender)
        {
            string topic = "CreateIncomingJob";
            StringBuilder properties = new StringBuilder();
            properties.AppendFormat("Customer:{0}", customer);
            properties.AppendFormat(",Order:{0}", order);
            properties.AppendFormat(",Source:{0}", source);
            properties.AppendFormat(",TransportationId:{0}", transportationId);
            return GetMessage(topic, properties.ToString(), sender);
        }

        public void CreateIncomingJob(uint customer, uint order, byte source, uint transportationId)
        {
            client.setMessage(CreateIncomingJob(customer, order, source, transportationId, client.senderName));
            client.sendMessage();
        }

        public byte[] AcceptIncomingJob (uint transportationId, string sender)
        {
            string topic = "AcceptIncomingJob";
            string properties = String.Format("TransportationId:{0}", transportationId);
            return GetMessage(topic, properties, sender);
        }

        public void AcceptIncomingJob(uint transportationId)
        {
            client.setMessage(AcceptIncomingJob(transportationId, client.senderName));
            client.sendMessage();
        }

        public byte[] ScanProduct (string sender)
        {
            string topic = "ScanProduct";
            string properties = String.Empty;
            return GetMessage(topic, properties, sender);
        }

        public void ScanProduct()
        {
            client.setMessage(ScanProduct(client.senderName));
            client.sendMessage();
        }

        public byte[] Ready(string sender)
        {
            string topic = "Ready";
            return GetMessage(topic, "", sender);
        }

        public void Ready()
        {
            client.setMessage(Ready(client.senderName));
            client.sendMessage();
        }

        public byte[] ResetDropoff(string sender)
        {
            string topic = "ResetDropoff";
            return GetMessage(topic, "", sender);
        }

        public void ResetDropoff()
        {
            client.setMessage(ResetDropoff(client.senderName));
            client.sendMessage();
        }

        public byte[] NewProduct (uint customer, uint order, byte color, uint inlay, byte[] productionSteps, string currentStep, byte priority, string sender)
        {
            string topic = "NewProduct";
            StringBuilder properties = new StringBuilder();
            properties.AppendFormat("Customer:{0}", customer);
            properties.AppendFormat(",Order:{0}", order);
            properties.AppendFormat(",Color:{0}", color);
            properties.AppendFormat(",Inlay:{0}", inlay);
            properties.AppendFormat(",CurrentStep:{0}", currentStep);
            properties.AppendFormat(",Priority:{0}", properties);
            if (productionSteps != null && productionSteps.Length > 0)
            {
                for (int step = 0; step < productionSteps.Length; step++)
                {
                    byte value = productionSteps[step];
                    properties.AppendFormat(",Step{0}:{1}", step, value);
                }
            }
            return GetMessage(topic, properties.ToString(), sender);
        }

        public void NewProduct(uint customer, uint order, byte color, uint inlay, byte[] productionSteps, string currentStep, byte priority)
        {
            client.setMessage(NewProduct(customer, order, color, inlay, productionSteps, currentStep, priority, client.senderName));
            client.sendMessage();
        }

        public byte[] SetDropoffComplete (uint transportationId, uint priority, byte moduleId, uint customer, uint order, string sender)
        {
            string topic = "SetDropoffComplete";
            StringBuilder properties = new StringBuilder();
            properties.AppendFormat("TransportationId:{0}", transportationId);
            properties.AppendFormat(",Priority:{0}", priority);
            properties.AppendFormat(",ModuleId:{0}", moduleId);
            properties.AppendFormat(",Customer:{0}", customer);
            properties.AppendFormat(",Order:{0}", order);
            return GetMessage(topic, properties.ToString(), sender);
        }

        public void SetDropoffComplete(uint transportationId, uint priority, byte moduleId, uint customer, uint order)
        {
            client.setMessage(SetDropoffComplete(transportationId, priority, moduleId, customer, order, client.senderName));
            client.sendMessage();
        }

        public byte[] ProductFinished (uint order, string sender)
        {
            string topic = "ProductFinished";
            string properties = String.Format("Order:{0}", order);
            return GetMessage(topic, properties, sender);
        }

        public void ProductFinished(uint order)
        {
            client.setMessage(ProductFinished(order, client.senderName));
            client.sendMessage();
        }

        public byte[] SetPickupRequest (uint customer, uint order, byte moduleId, uint priority, string sender)
        {
            string topic = "SetPickupRequest";
            StringBuilder properties = new StringBuilder();
            properties.AppendFormat("Customer:{0}", customer);
            properties.AppendFormat(",Order:{0}", order);
            properties.AppendFormat(",ModuleId:{0}", moduleId);
            properties.AppendFormat(",Priority:{0}", priority);
            return GetMessage(topic, properties.ToString(), sender);
        }

        public void SetPickupRequest(uint customer, uint order, byte moduleId, uint priority)
        {
            client.setMessage(SetPickupRequest(customer, order, moduleId, priority, client.senderName));
            client.sendMessage();
        }

        public byte[] PickupConfirmed (byte target, uint transportationId, string sender)
        {
            string topic = "PickupConfirmed";
            StringBuilder properties = new StringBuilder();
            properties.AppendFormat("Target:{0}", target);
            properties.AppendFormat(",TransportationId:{0}", transportationId);
            return GetMessage(topic, properties.ToString(), sender);
        }

        public void PickupConfirmed(byte target, uint transportationId)
        {
            client.setMessage(PickupConfirmed(target, transportationId, client.senderName));
            client.sendMessage();
        }

        public byte[] PickupFinished (uint transportationId, string sender)
        {
            string topic = "PickupFinished";
            string properties = String.Format("TransportationId:{0}", transportationId);
            return GetMessage(topic, properties, sender);
        }

        public void PickupFinished(uint transportationId)
        {
            client.setMessage(PickupFinished(transportationId, client.senderName));
            client.sendMessage();
        }

        public byte[] DeleteIncomingJob (uint transportationId, string sender)
        {
            string topic = "DeleteIncomingJob";
            string properties = String.Format("TransportationId:{0}", transportationId);
            return GetMessage(topic, properties, sender);
        }

        public void DeleteIncomingJob(uint transportationId)
        {
            client.setMessage(DeleteIncomingJob(transportationId, client.senderName));
            client.sendMessage();
        }

        public byte[] IncomingJobDeleted (uint transportationId, string sender)
        {
            string topic = "IncomingJobDeleted";
            string properties = String.Format("TransportationId:{0}", transportationId);
            return GetMessage(topic, properties, sender);
        }

        public void IncomingJobDeleted(uint transportationId)
        {
            client.setMessage(IncomingJobDeleted(transportationId, client.senderName));
            client.sendMessage();
        }
    }



public class MessageTester
{
  static public void Main(string[] args)
  {
    MessagingClient client = new MessagingClient("192.168.1.187", 9999, "weighing station");
    MessageBuilder msgBuilder = new MessageBuilder(client);
    if (args[0] == "part") {
      string partId = args[1];
      string amountStr = args[2];
      string position = args[3];
      int amount = int.Parse(amountStr, System.Globalization.CultureInfo.InvariantCulture);
      msgBuilder.ReportFetchedPart(partId, amount, position);
    } else  if (args[0] == "inventory") {
      string partId = args[1];
      string position = args[2];
      msgBuilder.ReportLowFilling(partId, position);
    }
    client.RequestStop();
  }
}
}
