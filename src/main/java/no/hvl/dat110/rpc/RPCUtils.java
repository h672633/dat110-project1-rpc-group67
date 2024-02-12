package no.hvl.dat110.rpc;

import java.io.Console;
import java.nio.ByteBuffer;
import java.util.Arrays;
import no.hvl.dat110.TODO;

public class RPCUtils {

	public static byte[] encapsulate(byte rpcid, byte[] payload) {

		byte[] rpcmsg = null;

		rpcmsg = new byte[payload.length+1];
		// TODO - START
		rpcmsg[0]=rpcid;
		for(int i = 0; i<payload.length;i++) {
			int j=i;
			rpcmsg[++j]=payload[i];
		}

		return rpcmsg;
	}

	public static byte[] decapsulate(byte[] rpcmsg) {

		byte[] payload = null;
		payload = new byte[rpcmsg.length-1];

		for(int i = 0;i<payload.length;i++) {
			int j = i;
			payload[i]=rpcmsg[++j];
		}

		return payload;

	}

	// convert String to byte array
	public static byte[] marshallString(String str) {

		byte[] encoded = null;
		encoded = new byte[str.length()];

		for(int i=0; i<str.length();i++) {
			encoded[i]=(byte) str.charAt(i);
		}


		return encoded;
	}

	// convert byte array to a String
	public static String unmarshallString(byte[] data) {

		String decoded = null;

		if(data == null) {
			return null;
		}

		for(int i=0;i<data.length;i++) {
		}
		decoded = new String(data);


		return decoded;
	}

	public static byte[] marshallVoid() {

		byte[] encoded = null;
		encoded = new byte[0];


		return encoded;

	}

	public static void unmarshallVoid(byte[] data) {

		// TODO

	}

	// convert boolean to a byte array representation
	public static byte[] marshallBoolean(boolean b) {

		byte[] encoded = new byte[1];

		if (b) {
			encoded[0] = 1;
		} else
		{
			encoded[0] = 0;
		}

		return encoded;
	}

	// convert byte array to a boolean representation
	public static boolean unmarshallBoolean(byte[] data) {

		return (data[0] > 0);

	}

	// integer to byte array representation
	public static byte[] marshallInteger(int x) {

		byte[] encoded = null;

		encoded = ByteBuffer.allocate(Integer.BYTES).putInt(x).array();


		return encoded;
	}

	// byte array representation to integer
	public static int unmarshallInteger(byte[] data) {

		int decoded = 0;


		for(int i=0;i<data.length;i++) {
			decoded+=(int) data	[i];
			System.out.print((int) data[i]);
		}
		decoded = decoded & 0xFF;

		return decoded;

	}
}
