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
$name = $_POST['username'];
$pass = $_POST['password'];
/*$name=1;
$pass=1;*/

$sql="select * from UserLogin where Username='$name' and Password='$pass' ";
$result = mysqli_query($con,$sql);
if( mysqli_query($con,$sql))
{$row = mysqli_fetch_array($result,MYSQLI_ASSOC);
echo json_encode($row);  }                                                         
                                                                                                  
mysqli_close($con);
?>
