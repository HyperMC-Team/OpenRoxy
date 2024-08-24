package net.optifine.util;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class ArrayUtils {
    public static boolean contains(Object[] arr, Object val) {
        if (arr == null) {
            return false;
        }
        for (int i = 0; i < arr.length; ++i) {
            Object object = arr[i];
            if (object != val) continue;
            return true;
        }
        return false;
    }

    public static int[] addIntsToArray(int[] intArray, int[] copyFrom) {
        if (intArray != null && copyFrom != null) {
            int i = intArray.length;
            int j = i + copyFrom.length;
            int[] aint = new int[j];
            System.arraycopy(intArray, 0, aint, 0, i);
            for (int k = 0; k < copyFrom.length; ++k) {
                aint[k + i] = copyFrom[k];
            }
            return aint;
        }
        throw new NullPointerException("The given array is NULL");
    }

    public static int[] addIntToArray(int[] intArray, int intValue) {
        return ArrayUtils.addIntsToArray(intArray, new int[]{intValue});
    }

    public static Object[] addObjectsToArray(Object[] arr, Object[] objs) {
        if (arr == null) {
            throw new NullPointerException("The given array is NULL");
        }
        if (objs.length == 0) {
            return arr;
        }
        int i = arr.length;
        int j = i + objs.length;
        Object[] aobject = (Object[])Array.newInstance(arr.getClass().getComponentType(), j);
        System.arraycopy(arr, 0, aobject, 0, i);
        System.arraycopy(objs, 0, aobject, i, objs.length);
        return aobject;
    }

    public static Object[] addObjectToArray(Object[] arr, Object obj) {
        if (arr == null) {
            throw new NullPointerException("The given array is NULL");
        }
        int i = arr.length;
        int j = i + 1;
        Object[] aobject = (Object[])Array.newInstance(arr.getClass().getComponentType(), j);
        System.arraycopy(arr, 0, aobject, 0, i);
        aobject[i] = obj;
        return aobject;
    }

    public static Object[] addObjectToArray(Object[] arr, Object obj, int index) {
        ArrayList<Object> list = new ArrayList<Object>(Arrays.asList(arr));
        list.add(index, obj);
        Object[] aobject = (Object[])Array.newInstance(arr.getClass().getComponentType(), list.size());
        return list.toArray(aobject);
    }

    public static String arrayToString(boolean[] arr, String separator) {
        if (arr == null) {
            return "";
        }
        StringBuffer stringbuffer = new StringBuffer(arr.length * 5);
        for (int i = 0; i < arr.length; ++i) {
            boolean flag = arr[i];
            if (i > 0) {
                stringbuffer.append(separator);
            }
            stringbuffer.append(String.valueOf(flag));
        }
        return stringbuffer.toString();
    }

    public static String arrayToString(float[] arr) {
        return ArrayUtils.arrayToString(arr, ", ");
    }

    public static String arrayToString(float[] arr, String separator) {
        if (arr == null) {
            return "";
        }
        StringBuffer stringbuffer = new StringBuffer(arr.length * 5);
        for (int i = 0; i < arr.length; ++i) {
            float f = arr[i];
            if (i > 0) {
                stringbuffer.append(separator);
            }
            stringbuffer.append(String.valueOf(f));
        }
        return stringbuffer.toString();
    }

    public static String arrayToString(float[] arr, String separator, String format) {
        if (arr == null) {
            return "";
        }
        StringBuffer stringbuffer = new StringBuffer(arr.length * 5);
        for (int i = 0; i < arr.length; ++i) {
            float f = arr[i];
            if (i > 0) {
                stringbuffer.append(separator);
            }
            stringbuffer.append(String.format(format, Float.valueOf(f)));
        }
        return stringbuffer.toString();
    }

    public static String arrayToString(int[] arr) {
        return ArrayUtils.arrayToString(arr, ", ");
    }

    public static String arrayToString(int[] arr, String separator) {
        if (arr == null) {
            return "";
        }
        StringBuffer stringbuffer = new StringBuffer(arr.length * 5);
        for (int i = 0; i < arr.length; ++i) {
            int j = arr[i];
            if (i > 0) {
                stringbuffer.append(separator);
            }
            stringbuffer.append(String.valueOf(j));
        }
        return stringbuffer.toString();
    }

    public static String arrayToHexString(int[] arr, String separator) {
        if (arr == null) {
            return "";
        }
        StringBuffer stringbuffer = new StringBuffer(arr.length * 5);
        for (int i = 0; i < arr.length; ++i) {
            int j = arr[i];
            if (i > 0) {
                stringbuffer.append(separator);
            }
            stringbuffer.append("0x");
            stringbuffer.append(Integer.toHexString(j));
        }
        return stringbuffer.toString();
    }

    public static String arrayToString(Object[] arr) {
        return ArrayUtils.arrayToString(arr, ", ");
    }

    public static String arrayToString(Object[] arr, String separator) {
        if (arr == null) {
            return "";
        }
        StringBuffer stringbuffer = new StringBuffer(arr.length * 5);
        for (int i = 0; i < arr.length; ++i) {
            Object object = arr[i];
            if (i > 0) {
                stringbuffer.append(separator);
            }
            stringbuffer.append(String.valueOf(object));
        }
        return stringbuffer.toString();
    }

    public static Object[] collectionToArray(Collection coll, Class elementClass) {
        if (coll == null) {
            return null;
        }
        if (elementClass == null) {
            return null;
        }
        if (elementClass.isPrimitive()) {
            throw new IllegalArgumentException("Can not make arrays with primitive elements (int, double), element class: " + String.valueOf(elementClass));
        }
        Object[] aobject = (Object[])Array.newInstance(elementClass, coll.size());
        return coll.toArray(aobject);
    }

    public static boolean equalsOne(int val, int[] vals) {
        for (int i = 0; i < vals.length; ++i) {
            if (vals[i] != val) continue;
            return true;
        }
        return false;
    }

    public static boolean equalsOne(Object a, Object[] bs) {
        if (bs == null) {
            return false;
        }
        for (int i = 0; i < bs.length; ++i) {
            Object object = bs[i];
            if (!ArrayUtils.equals(a, object)) continue;
            return true;
        }
        return false;
    }

    public static boolean equals(Object o1, Object o2) {
        return o1 == o2 ? true : (o1 == null ? false : o1.equals(o2));
    }

    public static boolean isSameOne(Object a, Object[] bs) {
        if (bs == null) {
            return false;
        }
        for (int i = 0; i < bs.length; ++i) {
            Object object = bs[i];
            if (a != object) continue;
            return true;
        }
        return false;
    }

    public static Object[] removeObjectFromArray(Object[] arr, Object obj) {
        ArrayList<Object> list = new ArrayList<Object>(Arrays.asList(arr));
        list.remove(obj);
        Object[] aobject = ArrayUtils.collectionToArray(list, arr.getClass().getComponentType());
        return aobject;
    }

    public static int[] toPrimitive(Integer[] arr) {
        if (arr == null) {
            return null;
        }
        if (arr.length == 0) {
            return new int[0];
        }
        int[] aint = new int[arr.length];
        for (int i = 0; i < aint.length; ++i) {
            aint[i] = arr[i];
        }
        return aint;
    }
}

