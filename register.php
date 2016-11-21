<?php                                                         
$host="mysql.hostinger.vn"; //replace with your hostname     
$username="u839687865_key"; //replace with your username      
$password="111111"; //replace with your password              
$db_name="u839687865_cwork"; //replace with your database     
$con=mysqli_connect("$host","$username","$password","$db_name");
if (mysqli_connect_errno($con))                                 
{                                                               
   echo '{"query_result":"ERROR"}';                             
}
//$name=5;$pass=1;$email=5;$company=1;
                                                               
$name = $_POST['username'];                                      
$pass = $_POST['password'];                                      
$email = $_POST['email'];                                 
$company = $_POST['company'];   

$check="select * from UserLogin where UserName='$name' or Email='$email'";

$result = mysqli_query($con,$check);
$count = mysqli_num_rows($result);

if ($count!=0) echo 'ton tai tai khoan';
else 
  { $sql="INSERT INTO UserLogin (UserName,Password,Email,Company) VALUES('$name','$pass','$email','$company')"; 
    if (mysqli_query($con, $sql)) {                                                                      
    echo 'success';                                                               
                              }                                                                     
    else{                                                                                                
     echo 'fail';                                             
  } 
}                                                                                                   
mysqli_close($con);                                                                                 
?>
