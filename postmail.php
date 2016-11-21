<?php
$mailto=$_POST['email'];
$mk=$_POST['mk'];

//$mailto='jknono1995@gmail.com';
//$mk='1111';

$message='your key is:'+$mk;
$subject='Repass your acount:';
$header = 'your key is:'+$mk;
 if ( mail($mailto, $subject, $message, $header) ) {
    echo "success";
} else {
    echo "Fail to send mail";
    echo $mailto;
    echo $mk; 
} //end if
?>

 