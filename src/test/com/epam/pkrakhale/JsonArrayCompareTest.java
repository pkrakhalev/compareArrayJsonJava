package test.com.epam.pkrakhale;

import com.google.common.collect.Sets;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class JsonArrayCompareTest {

    private final JSONArray arr1 = new JSONArray();
    private final JSONArray arr2 = new JSONArray();

    @Before
    public void beforeEachTestMethod() {
        JSONObject jo1 = new JSONObject();
        jo1.put("id", 1);
        jo1.put("name", "Doe");
        jo1.put("type", "PC");

        JSONObject jo2 = new JSONObject();
        jo2.put("id", 2);
        jo2.put("name", "Doe2");
        jo2.put("type", "PC2");

        JSONObject jo3 = new JSONObject();
        jo3.put("id", 3);
        jo3.put("name", "Doe3");
        jo3.put("type", "PC3");

        arr1.put(jo1);
        arr1.put(jo3);

        arr2.put(jo1);
        arr2.put(jo2);
    }

    @Test
    public void compareArraysTest() {
        Set<Object> difference = compare(arr1, arr2);
        System.out.println("Difference: " + Arrays.toString(difference.toArray()));
    }

    //I want to compare these 2 jsonArray, if these 2 jsonArray are different, Print the reason:
    //which json object is missing in actual jsonarray
    //which json object appeared in actual jsonarray but it should not appear
    //We expect a common function to accept the 2 jsonArray and return the difference
    private Set<Object> compare(final JSONArray actualArray, final JSONArray expectedArray) {
        Set<Object> actualSet = arrayToSet(actualArray);
        Set<Object> expectedSet = arrayToSet(expectedArray);
        Set<Object> copyActualSet = new HashSet<>(actualSet);

        System.out.println("Actual array: " + Arrays.toString(actualSet.toArray()));
        System.out.println("Expected array: " + Arrays.toString(expectedSet.toArray()));
        actualSet.removeAll(expectedSet);
        Set<Object> difference = new HashSet<>(actualSet);
        System.out.println("Appear in actual array: " + Arrays.toString(actualSet.toArray()));
        expectedSet.removeAll(copyActualSet);
        difference.addAll(expectedSet);
        System.out.println("Missed in actual array: " + Arrays.toString(expectedSet.toArray()));
        return difference;
    }

    @Test
    public void compareArraysUsingGoogleTest() {
        Set<Object> actualSet = arrayToSet(arr1);
        Set<Object> expectedSet = arrayToSet(arr2);
        Set<Object> differenceGoogle = Sets.symmetricDifference(actualSet, expectedSet);
        System.out.println("Difference: " + Arrays.toString(differenceGoogle.toArray()));
    }

    private Set<Object> arrayToSet(JSONArray array) {
        Set<Object> set = new HashSet<>();
        for (int i = 0; i < array.length(); i++) {
            set.add(array.get(i));
        }
        return set;
    }
}