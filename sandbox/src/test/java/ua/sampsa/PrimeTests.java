package ua.sampsa;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by Maryna Tkachuk on 24.12.20.
 */
public class PrimeTests {
  @Test
  public void testPrime()
  {
    Assert.assertTrue(Primes.isPrime(Integer.MAX_VALUE));
  }

  @Test
  public void testPrimeLong() {
    long n = Integer.MAX_VALUE;
    Assert.assertTrue(Primes.isPrime(n));
  }

  @Test
  public void testNonPrime()
  {
    Assert.assertFalse(Primes.isPrime(Integer.MAX_VALUE - 2));
  }


}
