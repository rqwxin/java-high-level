package com.cgx.master;

public class Main {

	public static void main(String[] args) {
		
		Master m = new Master(4, new Worker());
		
		for (int i = 0; i < 100; i++) {
			Task t = new Task();
			t.setId((long)i);
			t.setResult(i+100);
			m.add(t);
		}
		long s = System.currentTimeMillis();
		m.executer();
	  int result =0	;
		while(true){
			if(m.isComplete()){
				result += m.getResult();
				break;
			}
		}
		System.out.println("运作耗时："+(System.currentTimeMillis()-s)/1000+"mi,运行结果:"+result);
	}

}
