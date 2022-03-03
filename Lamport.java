import java.lang.reflect.*;

/*
 * class MyKey is a field of class MyNode
 * the method getValueOfKey permit to access at the 
 * param of the object key that is declare private
 * there are two method to access at the param of key
 * one even param for key primitive or string
 * one with param for key that are object
 * for correctnes the second method verify that key is not primitive nor string
*/
class MyKey {
    private Object key;
	private String field;

    public MyKey(Object w){
	    this.key=w;
		this.field=null;
	}

    public MyKey(Object w,String field){
	     this.key= (w);
		 this.field=field;
	}

	public Object returnKey(){
	    return this.key;
	}

	public Object getValueOfKey(){
		Object tt3=null,tt1=null;
		boolean bool = true;
	     try{
			if(((this.key) instanceof String))tt3=this.key;
			else{
		       tt1=this.key.getClass().getField("TYPE").get(this.key);
		       //System.out.println((this.key instanceof String));
		       if(((Class)(tt1)).isPrimitive())
				   tt3 = this.key;
			}
		}catch(NoSuchFieldException | IllegalAccessException | NullPointerException e1) {
			try{
			   //tt3=Class.forName(cl).getField(ev1).get(this.key);
			   tt3 = this.key.getClass().getField(this.field).get(this.key);}
			catch(NoSuchFieldException | NullPointerException | IllegalAccessException e2){
				bool=false;
				System.out.println("Object retrive: " + e2);
		    }
		}
		return (bool)?tt3:null;
	}


    public Object getValueOfKey(String ev1){
		Object tt3=null,tt1=null;
		boolean bool = true;
		try{
			if(((this.key) instanceof String))tt3=this.key;
			else{
		       tt1=this.key.getClass().getField("TYPE").get(this.key);
		       //System.out.println((this.key instanceof String));
		       if(((Class)(tt1)).isPrimitive())tt3=this.key;
			}
		}catch(NoSuchFieldException | IllegalAccessException | NullPointerException e1) {
		   try{
			   //tt3=Class.forName(cl).getField(ev1).get(this.key);
			   tt3 = this.key.getClass().getField(ev1).get(this.key);}
			catch(NoSuchFieldException | NullPointerException | IllegalAccessException e2){
				bool=false;
				System.out.println("Object retrive: " + e2);
		    }
		}		
		
		return (bool)?tt3:null;
	}
}

/*
 * This class is the base for the implementation of
 * interface ListAbs.
 * the two param next is the next node and key is the object that differentiated
 * the adiacent node other the potition between them
*/
class MyNode{
    public MyNode next=null;
	private MyKey keynode;
	public MyNode(MyKey w){
	    this.keynode=w;
	}
	public MyKey getKeyNode(){
	    return this.keynode;
	}
	public Object returnValueOfKey(String field){
	    return this.keynode.getValueOfKey(field);
	}
	public Object returnValueOfKey(){
	    return this.keynode.getValueOfKey();
	}
}



interface ListAbs{

	public boolean isEmpty();
	public int count();
    public String toString();

}

interface StkQue extends ListAbs{
	public void put(MyNode n);
	public MyKey get();
}

interface ListSimple extends ListAbs{
	public void put(MyNode n,Object o);
	public MyKey get(Object o);
	public MyNode find(Object o);
}

/*
 * MyStack has two costructor. One for key that
 * are primitive or string and an other for composite
 * the param obj set the type of object key
 * the param field set the field that have been examined
 *  the method setField can change this param
 * object.
 * Push to put a object
 * Pop to delete a object
*/

class MyStack implements StkQue{
	private MyNode insertN=null;

	public void put(MyNode n){
		if(this.insertN==null){
			this.insertN=n;
		    n.next=null;
		}
		else{
		    MyNode t = this.insertN;
	        this.insertN = n;
		    n.next = t;
		}
	}
	public void push(MyNode n){
	    this.put(n);
	}
	public MyKey get(){
	    MyKey tt = this.insertN.getKeyNode() ;
		MyNode t = this.insertN.next;
		this.insertN = t;
		return tt;
	}
	public MyKey pop(){
	    return this.get();
	}

	public boolean isEmpty(){
	    return this.insertN==null;
	}

	public int count(){
		int i=0;
		MyNode t = this.insertN;
	    while(t.next!=null){
			i++;
            t=t.next;
		}
	    return (i!=0)? ++i : i;
	}

	public String toString(){
		 MyNode t= this.insertN;
	     String str="";
		 while(t!=null){
			 str+=((t.returnValueOfKey())+ " - ");
			 t=t.next;
		 }
		 //if(t!=null)str+=(((bool)? t.returnValueOfKey(this.obj,this.field):t.getKeyNode().getValueOfKey()) + " - ");
         return str;
	}
}
/*
 * class my queue implement aqueue where put add object node
 * and get delete object node.
 * this class have two costructor one for object key:
 * if key is primitive not parameter in the constructor has necessary
 */
class MyQueue implements StkQue{
    private MyNode head=null,tail=null;

	public void put(MyNode n){
		MyNode t;

		t=this.tail; this.tail=n; this.tail.next=null;
		if(this.head==null) this.head=this.tail; else t.next=this.tail;
	
	}

	public MyKey get(){
	    MyKey k;
		MyNode t;

		k = this.head.getKeyNode();
		t= this.head.next;
		this.head = t;

		return k;
	}

	public boolean isEmpty(){
	    return this.head==null;
	}

	public int count(){
	    int i =0;
		MyNode t= this.head;
		while(t.next!=null){
			i++;
			t=t.next;
		}

		return (i!=0)? ++i : i;
	}

	public String toString(){
	     String str="";
	     MyNode t=this.head;
		 while(t!=null){
			 str+=( (t.returnValueOfKey()) + " - ");
			 t=t.next;
		 }
		 //if(t!=null)str+=(((bool)? t.returnValueOfKey(this.obj,this.field):t.getKeyNode().getValueOfKey()) + " - ");
         return str;

	}


}

class MyList implements ListSimple{
	private MyNode head=null,previus = null,tail=null;

    public MyNode find(Object o){
	    MyNode t = this.head;
		Class a,b;
		Object key;
		if(t!=null){
			key= t.returnValueOfKey() ;
		    a= (key.getClass());
		    b= (o.getClass());
			//System.out.println((a.cast(key)).equals(b.cast(o))+ " " +a+ " "+b);
		    while(t != null && !(a.cast(key)).equals(b.cast(o)) ){//
		        this.previus = t;
			    t = t.next;
			    if(t!=null){
			        key = t.returnValueOfKey();
				}
			 }
		}
		
		return t;
	}

    public MyNode find(Object o,String field){
	    MyNode t = this.head;
		Class a,b;
		Object key;
		if(t!=null){
			key= t.returnValueOfKey(field);
		    a= (key.getClass());
		    b= (o.getClass());
			//System.out.println((a.cast(key)).equals(b.cast(o))+ " " +a+ " "+b);
		    while(t != null && !(a.cast(key)).equals(b.cast(o)) ){//
		        this.previus = t;
			    t = t.next;
			    if(t!=null){
			        key = t.returnValueOfKey(field);
				}
			 }
		}
		
		return t;
	}

	public MyKey get(Object ob){
		MyNode t = this.find(ob);
	    MyKey k = null;
		if(t!=null){
			k = t.getKeyNode();
		    if(t==this.tail){
				if(this.tail==this.head) this.head=null;
				else{
			        this.tail=this.previus;
				    this.previus.next=null;
			    }
			}
		    else{
			    if(t==this.head)
                    this.head= this.head.next;
				else
				    this.previus.next=t.next;
			}
	    }

		return k;	
	}

	public MyKey get(Object ob, String field){
		MyNode t = this.find(ob,field);
	    MyKey k = null;
		if(t!=null){
			k = t.getKeyNode();
		    if(t==this.tail){
				if(this.tail==this.head) this.head=null;
				else{
			        this.tail=this.previus;
				    this.previus.next=null;
			    }
			}
		    else{
			    if(t==this.head)
                    this.head= this.head.next;
				else
				    this.previus.next=t.next;
			}
	    }

		return k;	
	}

	public void put(MyNode n,Object ob){
	    MyNode t;
		if(ob == null) t=this.tail;
		else t= this.find(ob);
		if(t==null){
			this.head=n;
			this.head.next=null;
			this.tail=this.head;
		}else{
		    if(t==this.tail){
			    this.tail.next=n;
				this.tail=n;
			    n.next= null;
			}
		    else{
			    MyNode s= t.next;
                t.next=n;
				n.next=s;
		    }
		}
	}

	public void put(MyNode n,Object ob,String field){
	    MyNode t;
		if(ob == null) t=this.tail;
		else t= this.find(ob,field);
		if(t==null){
			this.head=n;
			this.head.next=null;
			this.tail=this.head;
		}else{
		    if(t==this.tail){
			    this.tail.next=n;
				this.tail=n;
			    n.next= null;
			}
		    else{
			    MyNode s= t.next;
                t.next=n;
				n.next=s;
		    }
		}
	}

	public boolean isEmpty(){
	     return this.head==null;
	}

	public int count(){
	     int i=0;
		 MyNode t = this.head;
		 while(t!= null){
		     i++;
			 t=t.next;
		 }

		 return i;
	}

	public String toString(){
	     String str="";
	     MyNode t=this.head;
		 while(t!=null){
			 str+=( (t.returnValueOfKey()) + " - " );
			 t=t.next;
		 }

         return str;
	}

	public boolean min(Object val,String field){
	     MyNode t = this.head;
		 boolean bool = true;
		 while(t!=null){
		    if (t.returnValueOfKey(field)<val){
		        bool=false;		    
				t=null;
			}else t= t.next;
		 }

		 return bool;
	}
}




class MyListSync implements ListSimple{
	private MyNode head=null,previus = null,tail=null;


    public synchronized MyNode find(Object o){
	    MyNode t = this.head;
		Class a,b;
		Object key;
		if(t!=null){
			key= t.returnValueOfKey() ;
		    a= (key.getClass());
		    b= (o.getClass());
			//System.out.println((a.cast(key)).equals(b.cast(o))+ " " +a+ " "+b);
		    while(t != null && !(a.cast(key)).equals(b.cast(o)) ){//
		        this.previus = t;
			    t = t.next;
			    if(t!=null){
			        key = t.returnValueOfKey();
				}
			 }
		}
		
		return t;
	}

    public synchronized MyNode find(Object o,String field){
	    MyNode t = this.head;
		Class a,b;
		Object key;
		if(t!=null){
			key= t.returnValueOfKey(field);
		    a= (key.getClass());
		    b= (o.getClass());
			//System.out.println((a.cast(key)).equals(b.cast(o))+ " " +a+ " "+b);
		    while(t != null && !(a.cast(key)).equals(b.cast(o)) ){//
		        this.previus = t;
			    t = t.next;
			    if(t!=null){
			        key = t.returnValueOfKey(field);
				}
			 }
		}
		
		return t;
	}
	public synchronized MyKey get(Object ob){
		while(this.isEmpty()){
		   try{wait();
			}catch(InterruptedException e){};
		}	
		MyNode t;
	    MyKey k = null;
		if(ob==null) t=this.head;
		else t = this.find(ob);
		if(t!=null){
			k = t.getKeyNode();
		    if(t==this.tail){
				if(this.tail==this.head) this.head=null;
				else{
			        this.tail=this.previus;
				    this.previus.next=null;
			    }
			}
		    else{
			    if(t==this.head)
                    this.head= this.head.next;
				else
				    this.previus.next=t.next;
			}
	    }

		return k;	
	}

	public synchronized MyKey get(Object ob,String field){
		while(this.isEmpty()){
		   try{wait();
			}catch(InterruptedException e){};
		}	
		MyNode t;
	    MyKey k = null;
		if(ob==null) t=this.head;
		else t = this.find(ob,field);
		if(t!=null){
			k = t.getKeyNode();
		    if(t==this.tail){
				if(this.tail==this.head) this.head=null;
				else{
			        this.tail=this.previus;
				    this.previus.next=null;
			    }
			}
		    else{
			    if(t==this.head)
                    this.head= this.head.next;
				else
				    this.previus.next=t.next;
			}
	    }

		return k;	
	}

	public synchronized void  put(MyNode n,Object ob){
	    MyNode t;
		try{
		  notify();
		}catch ( IllegalMonitorStateException  e) { }
		if(ob == null) t=this.tail;
		else t= this.find(ob);
		if(t==null){
			this.head=n;
			this.head.next=null;
			this.tail=this.head;
		}else{
		    if(t==this.tail){
			    this.tail.next=n;
				this.tail=n;
			    n.next= null;
			}
		    else{
			    MyNode s= t.next;
                t.next=n;
				n.next=s;
		    }
		}
	}

	public synchronized void  put(MyNode n,Object ob,String field){
	    MyNode t;
		try{
		  notify();
		}catch ( IllegalMonitorStateException  e) { }
		if(ob == null) t=this.tail;
		else t= this.find(ob,field);
		if(t==null){
			this.head=n;
			this.head.next=null;
			this.tail=this.head;
		}else{
		    if(t==this.tail){
			    this.tail.next=n;
				this.tail=n;
			    n.next= null;
			}
		    else{
			    MyNode s= t.next;
                t.next=n;
				n.next=s;
		    }
		}
	}

	public synchronized boolean isEmpty(){
	     return this.head==null;
	}

	public synchronized int count(){
	     int i=0;
		 MyNode t = this.head;
		 while(t!= null){
		     i++;
			 t=t.next;
		 }

		 return i;
	}

	public synchronized String toString(){
	     String str="";
	     MyNode t=this.head;
		 while(t!=null){
			 str+=( (t.returnValueOfKey()) + " - " );
			 t=t.next;
		 }

         return str;
	}
}

class TM{
	public int num;
	public int client;
	public int tm;

	public TM(int num,int client,int tm){
	   this.num=num;
       this.client=client;
	   this.tm=tm;
	}
}

class Ack{
    public int num;
	public int client;

	public TM(int num,int client,int tm){
	   this.num=num;
       this.client=client;
	}

}

class TimeStamp{

	private int tm = 0;

	public synchronized int returnTimes(){
	     return this.tm++;
	}
}

class Resource{

	public String toString(){
	    return ("RESOURSE : " + Thread.currentThread());
	}
}

class Client extends Thread{

    private int num;
	private int totalClient;
	private Resource share;
	private TimeStamp times;
    private MyListSync in=null,out;
	private MyList tmlist,acklist;



	public Client(int num,MyListSync out,TimeStamp times,Resource share,int totalClient){
	    this.num=num;
		this.out=out;
		this.share=share;
		this.times = times;
		this.totalClient=totalClient;
		this.tmlist= new MyList();
		this.acklist = new MyList();
	}

	public int returnNum(){
	    return this.num;
	}

	public void setListIn(MyListSync in){
	    this.in=in;
	}

	public void run(){
		int i = 0;
		MyKey k;

		/*Aspetta che la risorsa coda in ingresso sia
         * sia allocata dal server
		 */
		while(this.in==null)
			;

        while(true){
             // Prende tutto ciò che c'è in coda in elo smista
		     while((k = this.in.get(null))!=null){
			     if(k.getClass().getName() == "TM")
					 this.tmlist.put(k,null);
				 else
					 this.acklist.put(k,null);
			 }
             //elimina tutti gli ack
			 while((k=this.acklist.get(null))!=null){
			     int i = k.returnValueOfKey("num");
				 this.tmlist.get(i,"num");
			 }
             //procede alla verifica se tocca lui
             if(findResourceRequest()){
			     if(verifiedAllClientResourceRequest()){
					 if(myRequestIsTheLess()){
						  System.out.println(this.share.toString());
					      this.tmlist.get(this.num,"num");
						  this.out.put(new MyNode(new MyKey(new Ack(this.num,null))),null)
					 }
				 
				 }
			 }else{
				 MyNode n = new MyNode(new MyKey(new TM(this.num,null,times.returnTimes())));
			     this.tmlist.put(n,null);
				 this.out.put(n,null);
			 }
             
		 }
	}

	private boolean findResourceRequest(){
	     MyKey k = this.tmlist.get(this.num,"num");
		 return (k!=null);
	}

	private boolean verifiedAllClientResourceRequest(){
	     int count = 0;
		 for(i=0; i<this.totalClient;i++){
		      if(this.tmlist.find(i)!= null)
				    count++;
		 }

		 return (count==this.totalClient); 
	}

	private boolean myRequestIsTheLess(){
	     MyNode myTm = this.tmlist.find(this.num);
		 int i = MyTm.returnValueOfKey("tm");
		 return this.tmlist.min(i,"tm");
	}

}

class Server extends Thread{
	private Client[] arr;
	private MyListSync in;
	private MyListSync[] out;
	private int num;

	public Server(int num,Client[] arr,MyListSync in){
		this.num=num;
	    this.arr=arr;
		this.in=in;
		this.out = new MyListSync[this.arr.length];
		for(int i=0;i<arr.length;i++){
			out[i] = new MyListSync();
            arr[i].setListIn(out[i]);
		}
	}

	public int returnNum(){
	    return this.num;
	}

	public void run(){
	    MyKey k;
		int client;
		while(true){
		   //System.out.println("server prima while");
		   while(!in.isEmpty()){
			   System.out.println("server dopo while");
		       k = in.get(null);
               client = (int)(k.getValueOfKey());
			   out[client].put(new MyNode(k),null);
		   }
		    
		}
	}
}

class Lamport{
	public static void main(String[] args){
	      Client[] arr = new Client[5];
	      Server s;
		  Resource share = new Resource();
		  TimeStamp tmsp = new TimeStamp();
		  int totalClient = 5;
		  MyListSync l = new MyListSync();
		  for(int i=0;i<totalClient;i++){
		      arr[i]= new Client(i,l,tmsp,share,totalClient);
			  arr[i].start();
		  }
		  s= new Server(100,arr,l);
		  s.start();

	}
}
