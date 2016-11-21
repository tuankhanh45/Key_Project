<?php
$host="mysql.hostinger.vn";  
$username="u839687865_key"; 
$password="111111"; 
$db_name="u839687865_cwork";  
$con=mysqli_connect("$host","$username","$password","$db_name");
if (mysqli_connect_errno($con))
{
   echo '{"query_result":"ERROR"}';
} 
$email = $_POST['email'];
$pass = $_POST['pass'];
//$mail='jknono1995@gmail.com';$pass='abc1';
$update="UPDATE UserLogin SET Password='$pass' WHERE Email='$email'";

//$update="UPDATE UserLogin SET Password='2222' WHERE Email='1'";
echo $email;
echo $pass;

mysqli_query($con,$update);

mysqli_close($con);
?>