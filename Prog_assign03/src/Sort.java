import java.util.ArrayList;
import java.util.Arrays;

public class Sort {
	
	ArrayList<int[]> list;

	public Sort()
	{
		System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------");
		System.out.println("		Random1000		Reverse1000		Random10000		Reverse10000		Random100000		Reverse100000");
		System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------");
	}
	public void exe()
	{
		bubble_time();
		selection_time();
		insert_time();
		merge_time();
		quick_time();
		heap_time();
		lib_time();
	}
	
	private void get_time(double time)
	{
		System.out.printf("%.5fsec		",(System.currentTimeMillis() - time) / 1000);
	}
	
	private void create_array()
	{
		list = new ArrayList<int[]>();
		int k = 1000;
		
		int i = 0;
		while(i<6)
		{
			list.add(new int[k]);
			for(int j = 0 ; j<k ; j++)			
				list.get(i)[j] = ((int)(Math.random()*(k-1)) +1);
			i++;
			list.add(new int[k]);
			for(int j = 0 ; j<k; j++)
				list.get(i)[j] = k-1-j;
			i++;
			k *= 10;
		}		
	}
	
	private void lib_time() {
		System.out.print("Library Sort	");
		create_array();
		for(int i = 0 ; i<6; i++)
		{
			long time = System.currentTimeMillis();
			Arrays.sort(list.get(i));
			get_time(time);
		}
		System.out.println("\n");
	}
	
	private void heap_time() {
		System.out.print("Heap Sort	");
		create_array();
		for(int i = 0 ; i<6; i++)
		{
			long time = System.currentTimeMillis();
			heap_sort(list.get(i));
			get_time(time);
		}
		System.out.println("\n");
	}
	
	private void quick_time()
	{
		for(int p = 0 ; p <5; p++)
		{
			create_array();			
			System.out.print("Quick ");
			if(p ==0)
				System.out.print("1		");
			else if (p ==1)
				System.out.print("2		");
			else if(p==2)
				System.out.print("3		");
			else if(p==3)
				System.out.print("4		");
			else
				System.out.print("5		");
			for(int i = 0 ; i<6; i++)
			{
				if(i == 5)
				{
					System.out.println("Stack Overflow");
					break;
				}
				long time = System.currentTimeMillis();
				quick_sort(list.get(i), 0 , list.get(i).length-1 , p);
				get_time(time);				
			}
			System.out.println("");
		}
	}
		
	private void merge_time() {
		System.out.print("Merge Sort	");
		create_array();
		for(int i = 0 ; i<6; i++)
		{
			long time = System.currentTimeMillis();
			merge_sort(list.get(i), 0 , list.get(i).length-1);
			get_time(time);
		}
		System.out.println("\n");
	}
	
	private void insert_time() {
		System.out.print("Insert Sort	");
		create_array();
		for(int i = 0 ; i<6; i++)
		{
			long time = System.currentTimeMillis();
			insert_sort(list.get(i));
			get_time(time);
		}
		System.out.println("\n");
	}

	private void selection_time() {
		System.out.print("Selection Sort	");
		create_array();
		for(int i = 0 ; i<6; i++)
		{
			long time = System.currentTimeMillis();
			selection_sort(list.get(i));
			get_time(time);
		}
		System.out.println("\n");	
	}

	private void bubble_time() {
		System.out.print("Bubble Sort	");
		create_array();
		for(int i = 0 ; i<6; i++)
		{
			long time = System.currentTimeMillis();
			bubble_sort(list.get(i));
			get_time(time);
		}
		System.out.println("\n");
	}

	private void bubble_sort(int[] arr)
	{
		for(int j = arr.length-1; j>=0; j--)
		{
			for(int i = 0; i<j; i++)
			{
				if(arr[i] > arr[i+1])
				{
					int tmp = arr[i];
					arr[i] = arr[i+1];
					arr[i+1] = tmp;
				}
			}
		}
	}

	private void selection_sort(int[] arr)
	{
		for(int i = arr.length-1; i>0; i--)
		{
			int max = 0;
			int num = 0;
			for(int j = 0; j<=i ; j++)
			{
				if(arr[j] > max)
				{
					max = arr[j];
					num = j;
				}
			}
			int tmp = arr[i];
			arr[i] = max;
			arr[num] = tmp;			
		}		
	}
	
	private void insert_sort(int[] arr)
	{
		for(int i = 1; i<arr.length; i++)
		{
			int index = 0;
			for(int j = 0; j<i; j++)
			{
				if(arr[i] > arr[j])
					index++;
				else
					break;		
			}
			int tmp = arr[i];
			for(int j = i; j>index ; j--)
				arr[j] = arr[j-1];
			arr[index] = tmp;
		}
	}
	
	private void merge_sort(int[] arr, int b, int e)
	{
		if(b<e)
		{
			int r = (b+e)/2;
			merge_sort(arr,b,r);
			merge_sort(arr,r+1,e);
			merge(arr, b,r,e);
		}		
	}

	private void merge(int[] arr, int b, int r, int e) {
		int[] tmp = new int[arr.length];
		int i = b;
		int k = b;
		int j = r+1;
		
		while(i<=r && j<=e)
		{
			if(arr[i] <= arr[j])
				tmp[k++] = arr[i++];
			else
				tmp[k++] = arr[j++];
		}		
		while(i<=r)
			tmp[k++] = arr[i++];
		while(j<=e)
			tmp[k++] = arr[j++];
		for(int m = b; m<=e ; m++)
			arr[m] = tmp[m];			
	}
	
	private void quick_sort(int[] arr, int b, int e, int p)
	{		
		if(b<e)
		{			
			action(arr,b,e,p);
			int r = partition(arr, b, e, p);
			quick_sort(arr,b,r-1,p);
			quick_sort(arr,r+1,e,p);
		}
	}
	
	private void action(int[] arr, int b, int e, int p)
	{
		if(p==1)
		{
			int tmp = arr[e];
			arr[e] = arr[b];
			arr[b] = tmp;
		}
		
		else if(p==2)
		{
			int tmp = arr[(b+e)/2];
			arr[e] = arr[(b+e)/2];
			arr[(b+e)/2] = tmp;
		}
		else if(p==3)
		{			
			int num = middle(arr,b,e,(b+e)/2);
			int tmp = arr[e];
			arr[e] = arr[num];
			arr[num] = tmp;		
		}
		else if(p==4)
		{
			int num = (int)(Math.random()*e);
			int tmp = arr[num];
			arr[num] = arr[e];
			arr[e] = tmp;
		}
	}

	private int partition(int[] arr, int b, int e, int p)
	{		
		int i = b - 1;
		int j = b;		
		while(j<e)
		{
			if(arr[j] < arr[e])
			{
				i++;
				int tmp = arr[j];
				arr[j] = arr[i];
				arr[i] = tmp;
				j++;
			}
			else
				j++;
		}		
		int tmp = arr[e];
		arr[e] = arr[i+1];
		arr[i+1] = tmp;		
		return i+1;
	}
	
	private int middle(int[] arr, int a, int b, int c)
	{
		if( (arr[a]< arr[b] && arr[b]< arr[c]) || (arr[c]<arr[b] && arr[b]<arr[a]))
			return b;
		else if( (arr[b]< arr[a] && arr[a]< arr[c]) || (arr[c]<arr[a] && arr[a]<arr[b]))
			return a;
		else
			return c;
	}
	
	private void heap_sort(int[] arr)
	{
		int k = arr.length;
		heap(arr, 0,k);
		while(k>0)
		{
			int tmp = arr[0];
			arr[0] = arr[k-1];
			arr[k-1] = tmp;
			heap(arr,0,-1+(k--));
		}
	}
	
	private void heap(int[] arr, int i, int j)
	{		
		if(check(arr,i,j))
			return;
		
		if(j==arr.length)
		{
			heap(arr,i*2+1,j);
			heap(arr,i*2+2,j);
		}
		
		int index;
		if(arr[i*2+1] > arr[i*2+2])
			index = i*2 +1;
		else
			index = i*2 +2;
		
		if(arr[i] >= arr[index])
			return;
		
		change(arr,i,index);
		heap(arr,index,j);		
	}
	
	private boolean check(int[] arr, int i, int j)
	{
		if(i*2+1>j-1)
			return true;
		if(i*2+1 <= j -1 && i*2+2 > j - 1)
		{
			if(arr[i]>arr[i*2+1])
				return true;
			change(arr,i,i*2+1);
			return true;
		}
		return false;
	}
	
	private void change(int[] arr, int i, int j)
	{
		int tmp = arr[j];
		arr[j] = arr[i];
		arr[i] = tmp;
	}
}