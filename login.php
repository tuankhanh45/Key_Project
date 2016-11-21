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

$sql="select * from UserLogin where Username='$name' and Password='$pass'";
$result = mysqli_query($con,$sql);
      $row = mysqli_fetch_array($result,MYSQLI_ASSOC);
      $count = mysqli_num_rows($result);	
      if($count == 1) {            
    echo 'success';}                                                                 
else{                                                                                                
    echo 'fail'; }             
mysqli_close($con);
?>
