package nikhil.examples.mockito.powermockito;


import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

/**
 * Unit test for simple App.
 */
public class AppTest
{

    App obj;

    /*
    Very simple test case. No mockito
     */
    @Test
    public void testSimpleValueChange(){

        obj = new App();
        obj.simpleMethod(5);
        Assert.assertEquals(5, obj.getaNormalVariable());
    }

    /*
    See App.doHeavyProcessing();

    We use mockito to mock the function call to justAnotherClass.doSomethingComplex()
    since the function takes too much time and we want to quickly run the unit test cases
    and not waste time on complexities.
     */
    @Test
    public void testdoHeavyProcessing() throws InterruptedException {

        obj = new App();


        /*
         STEP 1:
         Mock the class whose function is going to be called
          */
        JustAnotherClass justAnotherClassMock = Mockito.mock(JustAnotherClass.class);

        /*
         STEP 2:
         Now that we have mocked the class, we define what happens when the mocked
         class's function is called.
        */
        Mockito.when(justAnotherClassMock.doSomethingComplex(Mockito.anyInt())).thenReturn(1);

        /*
          STEP 3:
          DO NOT forget to set the mocked class's object as the class's object in the
          actual class
          */
        obj.setJustAnotherClass(justAnotherClassMock);


        /*
        Since mockito will always return 1, and doHeavyProcessing() does nothing
        but call justAnogtherClass.doSomethingComplex(), the value returned must
        always be 1.
         */
        int valueWeGet = obj.doHeavyProcessing();
        Assert.assertEquals(valueWeGet, 1);
    }

}
