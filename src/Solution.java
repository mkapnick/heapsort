import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * User: Michael Kapnick
 * Date: 2/16/14
 * Squarespace solution
 */
public class Solution
{
    public static void main(String args[] ) throws Exception
    {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT */
        System.out.println(" -- START -- ");

        Heap <Number> heap;

        heap = setUp();
        heap.organizeIntoHeap();
        heap.removeAndOutput();


    }

    /**
     * Read input from SDTIN and create a Heap object as necessary
     *
     * @return A type safe Heap object
     */
    private static Heap <Number> setUp() throws Exception
    {

        BufferedReader      bufferedReader;
        Scanner             keyboard;
        String              heapStructure;
        ArrayList<Double>   list;
        Heap                heap;
        String              heapData;

        keyboard            = new Scanner(System.in);
        list                = new ArrayList<Double>();
        bufferedReader      = new BufferedReader (new InputStreamReader(System.in));

        // determine max-heap or min-heap, check for errors
        do
        {
            heapStructure   = keyboard.next();
        }   while(!isStructureOk(heapStructure));

        //read user input
        while(!(heapData = bufferedReader.readLine()).equals(""))
        {
            try
            {
                list.add(Double.parseDouble(heapData)); //treat integers as doubles
            }
            catch(Exception e)
            {
                try
                {
                    //user entered a string
                    heapData = heapData.replaceAll("^\"|\"$", "");
                    list.add(Double.parseDouble(heapData));
                }

                catch (Exception ex)
                {
                    System.out.println("Bad input");
                }
            }
        }

        if(heapStructure.equalsIgnoreCase("max-heap"))
            heap = new MaxHeap<Double>(list);
        else
            heap = new MinHeap<Double>(list);

        return heap;
    }

    /**
     * Checks to make sure the user enters max-heap or min-heap from STDIN
     *
     * @param userInput input from the user
     * @return ok       user input is valid or not
     */
    private static boolean isStructureOk(String userInput)
    {
        boolean ok;
        ok = false;

        if (userInput.equalsIgnoreCase("max-heap") || userInput.equalsIgnoreCase("min-heap"))
            ok = true;
        else
            System.out.print("Incorrect input\n");

        return ok;
    }

    /******************************************************************************************
     *  An abstraction of the Heap data structure
     *
     *  @param <T> Type safety.
     *****************************************************************************************/
    public static abstract class Heap <T extends Number>
    {
        protected final ArrayList<T>            values;
        protected ArrayList <T>                 heap;
        protected final int                     size;
        protected int                           count;

        /**
         * Explicit value constructor
         *
         * @param values A list of values inserted by the user
         */
        public Heap(ArrayList<T> values)
        {
            this.values         = values;
            this.size           = values.size();
            this.heap           = new ArrayList<T>();
            this.count          = 0;
        }

        /**
         * Insert a value into the heap
         *
         * @param value the value to be inserted into the heap
         */
        public void insert(T value)
        {
            this.heap.add(value);
            siftUp(count);
            count++;

        }

        /**
         * Organize linear arraylist of values into a heap structure
         *
         */
        public void organizeIntoHeap()
        {
            if(this.size > 0)
            {
                for(int i =0; i < this.size; i++)
                {
                    this.insert(this.values.get(i));
                }
            }
        }

        /**
         * Peek at the first value in the heap without removing it
         *
         * @return the first value in the heap
         */
        public T peek()
        {
            return this.heap.get(0);
        }

        /**
         * Remove the first value from the heap
         *
         * @return the first value from the heap
         */
        public T remove()
        {
            T value;
            value = this.heap.get(0);

            count--;
            this.heap.set(0, this.heap.get(count)); //copy lowest leaf to the root and sift it down

            this.heap.remove(count);

            siftDown(0);

            return value;

        }

        /**
         *  Remove and output each element in the heap
         *
         */
        public void removeAndOutput()
        {
            for(int i =0; i < this.size(); i++)
            {
                System.out.println(this.remove());
            }
        }

        public abstract void siftDown(int index);
        public abstract void siftUp(int index);

        /**
         * Size of the heap
         *
         * @return the size of the heap
         */
        public int size()
        {
            return size;
        }
    }


    /******************************************************************************************
     *  A concrete Heap class
     *
     *
     * @param <T> Type safety
     *****************************************************************************************/
    public static class MaxHeap <T extends Number>  extends Heap <T>
    {
        public MaxHeap(ArrayList<T> values)
        {
           super(values);
        }

        /**
         *
         * @param index
         */
        public void siftDown(int index)
        {
            int     childLeft, childRight,largest;
            boolean ok;

            childLeft   = (2 * index) + 1;
            childRight  = (2 * index) + 2;
            ok          = false;

            largest     = index;

            if (childLeft < count && this.heap.get(childLeft).doubleValue() >= this.heap.get(largest).doubleValue())
            {
                if(childRight < count)
                {
                    if (this.heap.get(childLeft).doubleValue() >= this.heap.get(childRight).doubleValue())
                    {
                        largest = childLeft;
                        ok      = true;
                    }
                }

                else
                {
                    largest = childLeft;
                    ok      = true;
                }
            }

            if (childRight < count && !ok && this.heap.get(childRight).doubleValue() >= this.heap.get(largest).doubleValue())
                largest = childRight;

            if (largest != index)
            {
                T tmp = this.heap.get(index);
                this.heap.set(index, this.heap.get(largest));
                this.heap.set(largest, tmp);

                siftDown(largest);
            }
        }

        /**
         *
         * @param index
         */
        public void siftUp(int index)
        {
             if (index > 0 && index < size)
             {
                 int parent;
                 parent = (index -1) / 2;

                 if(this.heap.get(index).doubleValue() >= this.heap.get(parent).doubleValue())
                 {
                     T temp = this.heap.get(index);
                     this.heap.set(index, this.heap.get(parent));
                     this.heap.set(parent, temp);

                     siftUp(parent);
                 }
             }
        }
    }

    /******************************************************************************************
     *   A concrete heap class
     *
     *
     * @param <T> Type safety
     *****************************************************************************************/
    public static class MinHeap <T extends Number> extends Heap <T>
    {
        public MinHeap(ArrayList<T> list)
        {
           super(list);
        }

        /**
         *
         * @param index
         */
        public void siftDown(int index)
        {
            int     childLeft, childRight,largest;
            boolean ok;

            childLeft   = (2 * index) + 1;
            childRight  = (2 * index) + 2;
            ok          = false;

            largest     = index;

            if (childLeft < count && this.heap.get(childLeft).doubleValue() <= this.heap.get(largest).doubleValue())
            {
                if(childRight < count)
                {
                    if (this.heap.get(childLeft).doubleValue() <= this.heap.get(childRight).doubleValue())
                    {
                        largest = childLeft;
                        ok      = true;
                    }
                }

                else
                {
                    largest = childLeft;
                    ok      = true;
                }
            }



            else if (childRight < count && !ok && this.heap.get(childRight).doubleValue() <= this.heap.get(largest).doubleValue())
                largest = childRight;

            if (largest != index)
            {
                T tmp = this.heap.get(index);
                this.heap.set(index, this.heap.get(largest));
                this.heap.set(largest, tmp);

                siftDown(largest);
            }
        }
        /**
         *
         * @param index
         */
        public void siftUp(int index)
        {
            if (index > 0 && index < size)
            {
                int parent;
                parent = (index -1) / 2;

                if(this.heap.get(index).doubleValue() <= this.heap.get(parent).doubleValue())
                {
                    T temp = this.heap.get(index);
                    this.heap.set(index, this.heap.get(parent));
                    this.heap.set(parent, temp);

                    siftUp(parent);
                }
            }
        }
    }
}

