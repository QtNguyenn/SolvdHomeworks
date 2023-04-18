public class insertionSort {


    void sort (int arr[])
    {
        int lenght = arr.length;
        for(int m = 0; m < lenght-1; m++)
        {
            for (int i = 0; i <lenght-1; i++)
            {
                if ( arr[i] > arr[i+1])
                {
                    int temp;

                    temp = arr[i];
                    arr[i] = arr[i+1];
                    arr[i+1] = temp;
                }
            }
        }
        
    }

    static void printSorted(int arr[])
    {
        int lenght = arr.length;
        for(int j = 0; j<lenght; j++)
        {
            System.out.print(arr[j]+ " ");
        }
    }


    public static void main(String args[])
    {
        int arr[] = { 12,2,20,11,11,13,5,6,9 };
 
        insertionSort ob = new insertionSort();
        ob.sort(arr);
 
       printSorted(arr);
    }
}


