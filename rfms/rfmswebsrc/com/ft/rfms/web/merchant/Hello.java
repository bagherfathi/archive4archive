package com.ft.rfms.web.merchant;

public class Hello implements java.io.Serializable {
	public String sayHello() {
		System.out.println("sayHello()������");
		return "Hello World!";
		// com.metaparadigm.jsonrpc.JSONRPCBridge = new
		// JSONRPCBridge a = new JSONRPCBridge();
	}

	public String sayHello(String name) {
		System.out.println("sayHello(String name)������");
		return "Hello " + name + "!";
	}

}
