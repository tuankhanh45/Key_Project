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

//test
/*$name=1;
$data='[{"Address":"ngo 11111 ","Longitude":"a","Latitude":"b","DateTime":"c"}]';    
echo $data."<br>";
echo $name."<br>";*/

//real
function chuyenChuoi($str) {
// In th&#432;&#7901;ng
     $str = preg_replace("/(à|á|&#7841;|&#7843;|ã|â|&#7847;|&#7845;|&#7853;|&#7849;|&#7851;|&#259;|&#7857;|&#7855;|&#7863;|&#7859;|&#7861;)/", 'a', $str);
     $str = preg_replace("/(è|é|&#7865;|&#7867;|&#7869;|ê|&#7873;|&#7871;|&#7879;|&#7875;|&#7877;)/", 'e', $str);
     $str = preg_replace("/(ì|í|&#7883;|&#7881;|&#297;)/", 'i', $str);
     $str = preg_replace("/(ò|ó|&#7885;|&#7887;|õ|ô|&#7891;|&#7889;|&#7897;|&#7893;|&#7895;|&#417;|&#7901;|&#7899;|&#7907;|&#7903;|&#7905;)/", 'o', $str);
     $str = preg_replace("/(ù|ú|&#7909;|&#7911;|&#361;|&#432;|&#7915;|&#7913;|&#7921;|&#7917;|&#7919;)/", 'u', $str);
     $str = preg_replace("/(&#7923;|ý|&#7925;|&#7927;|&#7929;)/", 'y', $str);
     $str = preg_replace("/(&#273;)/", 'd', $str);    
// In &#273;&#7853;m
     $str = preg_replace("/(À|Á|&#7840;|&#7842;|Ã|Â|&#7846;|&#7844;|&#7852;|&#7848;|&#7850;|&#258;|&#7856;|&#7854;|&#7862;|&#7858;|&#7860;)/", 'A', $str);
     $str = preg_replace("/(È|É|&#7864;|&#7866;|&#7868;|Ê|&#7872;|&#7870;|&#7878;|&#7874;|&#7876;)/", 'E', $str);
     $str = preg_replace("/(Ì|Í|&#7882;|&#7880;|&#296;)/", 'I', $str);
     $str = preg_replace("/(Ò|Ó|&#7884;|&#7886;|Õ|Ô|&#7890;|&#7888;|&#7896;|&#7892;|&#7894;|&#416;|&#7900;|&#7898;|&#7906;|&#7902;|&#7904;)/", 'O', $str);
     $str = preg_replace("/(Ù|Ú|&#7908;|&#7910;|&#360;|&#431;|&#7914;|&#7912;|&#7920;|&#7916;|&#7918;)/", 'U', $str);
     $str = preg_replace("/(&#7922;|Ý|&#7924;|&#7926;|&#7928;)/", 'Y', $str);
     $str = preg_replace("/(&#272;)/", 'D', $str);
     return $str; // Tr&#7843; v&#7873; chu&#7895;i &#273;ã chuy&#7875;n
}  

$data1=$_POST['JsonArrayPost'];

$name =$_POST['UserName'];
$data=chuyenChuoi($data1);

$json = json_decode($data,true);
echo test;
echo "post:"."<br>";
echo $data1;
echo "json: "."<br>";
echo $data;
echo $json;
for($i=0; $i < count($json); $i++)
	
{   //take data from JsonArrayPost
        //test    
        echo $json[$i]['Address']."<br>";
        //real
        $latitude=$json[$i]['Latitude'];
	$longitude=$json[$i]['Longitude'];
	$datetime=$json[$i]['DateTime'];
	$address=$json[$i]['Address'];

        echo $latitude."<br>";
        echo $longitude."<br>";
        echo $datetime."<br>";
        echo $address."<br>";

       //insert to db
	$sqladd="INSERT INTO  GPS (UserName,Data,Latitude,Longitude,DateTime,Address) 
	                   VALUES('$name','$data','$latitude','$longitude','$datetime','$address')";
        mysqli_query($con,$sqladd);
}
mysqli_close($con);
?>

 