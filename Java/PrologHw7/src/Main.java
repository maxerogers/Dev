
public class Main {
	public static void main(String[] args)
	{
		Store jcp = new Store("jcp",5,1,1);
		Store tommy = new Store("tommy",10,5,5);
		Store wal = new Store("wal",5,5,5);
		Store[] stores = new Store[3];
		stores[0] = wal;
		stores[1] = jcp;
		stores[2] = tommy;
		Link l1 = new Link(wal,jcp);
		Link l2 = new Link(tommy,jcp);
		Link[] links = new Link[2];
		links[0] = l1;
		links[1] = l2;
		buy(stores,links,17);
	}
	public static void buy(Store[] s, Link[] l,int MC)
	{
		
	}
	public static class Link
	{
		public Store s1;
		public Store s2;
		Link(Store x, Store y)
		{
			s1 = x; s2 = y;
		}
	}
	public static class Store
	{
		public String name;
		public int Capacity;
		public int Bankrupt;
		public int Waiting;
		Store(String n, int c, int b, int w)
		{name = n;Capacity = c;Bankrupt = b; Waiting = w;}
	}
}
