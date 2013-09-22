/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package localaddress.util;
import java.util.*;
import localaddress.Entity.*;
/**
 *
 * @author kanishka
 */
public class ContactCompare implements Comparator{
/*
 the value 0 should be set if you wan to sort by the  first name and the 1 to sort by the last name;
 */
public  int sortcrieteria=0;
@Override
public int compare(Object oo1,Object oo2){
    int ret;
    Contacts o1=(Contacts)oo1;
    Contacts o2=(Contacts)oo2;


  if(sortcrieteria==0){
      ret=o1.FName.compareTo(o2.FName);
  }else{
      ret=o1.LName.compareTo(o2.LName);
  }
    
    return ret;
}
    
    
    
}
