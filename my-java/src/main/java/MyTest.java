/*
 * Copyright (C) 2008 Sabre Polska, All rights reserved.
 *
 * This software is the confidential and proprietary product of Sabre Polska. 
 * Any unauthorized use, reproduction, or transfer of this software is 
 * strictly prohibited.
 *
 * creation date: Aug 26, 2010
 */



import org.junit.Test;

/**
 * @author Bartosz Wieczorek
 * 
 */
public class MyTest {

   private int counter = 0;

   @Test
   public void a() {
      System.out.println(counter++);
   }

   @Test
   public void b() {
      System.out.println(counter++);
   }

   @Test
   public void c() {
      System.out.println(counter++);
   }

}
