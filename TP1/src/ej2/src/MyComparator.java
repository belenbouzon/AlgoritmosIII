import java.util.Comparator;

public class MyComparator implements Comparator<Integer>
{
    public int compare( Integer x, Integer y )
    {
        return y - x;
    }
}