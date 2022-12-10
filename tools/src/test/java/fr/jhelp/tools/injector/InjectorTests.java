package fr.jhelp.tools.injector;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InjectorTests {
    @Before
    public void initialization() {
        // Clear previous injections to let tests independent
        Injector.clearInjections();
    }

    @Test
    public void requestNotInjectedClass() {
        try {
            final String read = Injector.instance(String.class);
            Assert.fail("Should throw IllegalArgumentException : " + read);
        } catch (IllegalArgumentException exception) {
            // That is what we expected
        }
    }

    @Test
    public void injectInterface() {
        Injector.inject(List.class, new ArrayList<>(Arrays.asList("Hello", "World", "!")));

        final List<String> listGet = Injector.instance(List.class);
        Assert.assertEquals(3, listGet.size());
        Assert.assertEquals("Hello", listGet.get(0));
        Assert.assertEquals("World", listGet.get(1));
        Assert.assertEquals("!", listGet.get(2));
    }

    @Test
    public void changeInjection() {
        Injector.inject(List.class, Arrays.asList("Hello", "World", "!"));

        List<String> listGet = Injector.instance(List.class);
        Assert.assertEquals(3, listGet.size());
        Assert.assertEquals("Hello", listGet.get(0));
        Assert.assertEquals("World", listGet.get(1));
        Assert.assertEquals("!", listGet.get(2));

        Injector.inject(List.class, Arrays.asList("42", "is", "the", "answer"));
        listGet = Injector.instance(List.class);
        Assert.assertEquals(4, listGet.size());
        Assert.assertEquals("42", listGet.get(0));
        Assert.assertEquals("is", listGet.get(1));
        Assert.assertEquals("the", listGet.get(2));
        Assert.assertEquals("answer", listGet.get(3));
    }


    @Test
    public void changeContentOfInjected() {
        final ArrayList<String> arrayList = new ArrayList<String>(Arrays.asList("Hello", "World", "!"));
        Injector.inject(List.class, arrayList);

        List<String> listGet = Injector.instance(List.class);
        Assert.assertEquals(3, listGet.size());
        Assert.assertEquals("Hello", listGet.get(0));
        Assert.assertEquals("World", listGet.get(1));
        Assert.assertEquals("!", listGet.get(2));

        arrayList.clear();
        arrayList.addAll(Arrays.asList("42", "is", "the", "answer"));
        listGet = Injector.instance(List.class);
        Assert.assertEquals(4, listGet.size());
        Assert.assertEquals("42", listGet.get(0));
        Assert.assertEquals("is", listGet.get(1));
        Assert.assertEquals("the", listGet.get(2));
        Assert.assertEquals("answer", listGet.get(3));
    }
}
