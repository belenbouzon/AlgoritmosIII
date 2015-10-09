
public class DobleTupla extends Object {
	public int a;
	public int b;
	public int c;
	public int d;
	public DobleTupla(int a,int b,int c,int d){
		this.a = a;
		this.b = b;
		this.c = c;
		this.d = d;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + a;
		result = prime * result + b;
		result = prime * result + c;
		result = prime * result + d;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DobleTupla other = (DobleTupla) obj;
		if (a != other.a)
			return false;
		if (b != other.b)
			return false;
		if (c != other.c)
			return false;
		if (d != other.d)
			return false;
		return true;
	}
	
}
