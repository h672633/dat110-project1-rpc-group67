package no.hvl.dat110.rpc;

import no.hvl.dat110.TODO;
import no.hvl.dat110.messaging.*;

public class RPCClient {

	// underlying messaging client used for RPC communication
	private MessagingClient msgclient;

	// underlying messaging connection used for RPC communication
	private MessageConnection connection;

	public RPCClient(String server, int port) {

		msgclient = new MessagingClient(server, port);
	}

	public void connect() {

		// TODO - START
		// connect using the RPC client
		this.connection = msgclient.connect();

		// TODO - END
	}

	public void disconnect() {

		// TODO - START
		// disconnect by closing the underlying messaging connection
		if(this.connection != null) {
			connection.close();
		}

		// TODO - END
	}

	/*
	 * Make a remote call om the method on the RPC server by sending an RPC request
	 * message and receive an RPC reply message
	 *
	 * rpcid is the identifier on the server side of the method to be called param
	 * is the marshalled parameter of the method to be called
	 */

	public byte[] call(byte rpcid, byte[] param) {

		byte[] returnval = null;
		byte[] encapsulated = null;


		if (param != null) {
			encapsulated = RPCUtils.encapsulate(rpcid, param);
		} else {
			return null;
		}


		boolean sjekk = false;

		while (sjekk != true) {

			Message requestMessage = new Message(encapsulated);
			connection.send(requestMessage);

			Message receivedMessage = connection.receive();

			if (receivedMessage != null) {
			returnval = RPCUtils.decapsulate(receivedMessage.getData());
			}

			sjekk = true;

		}

		encapsulated = RPCUtils.encapsulate(rpcid, param);
		Message rpcmsg = new Message(encapsulated);
		connection.send(rpcmsg);
		Message reply = connection.receive();
		byte[] rpcreply = reply.getData();
		returnval = RPCUtils.decapsulate(rpcreply);

		// TODO - END
		return returnval;

	}

}