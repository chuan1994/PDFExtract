package testCases;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import testCases.masterThesisTests.*;
import testCases.PHDThesisTests.*;

@RunWith(Suite.class)
@SuiteClasses({
	MasterTests1.class,
	MasterTests2.class,
	PHDTests1.class,
	PHDTests2.class,
	PHDTests3.class
})
public class AllTests {

}
