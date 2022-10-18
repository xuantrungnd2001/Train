<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
    <form action="xxe.php" enctype="multipart/form-data" method="post">
    Select XML File to upload:
  <input type="file" name="xmlfile" id="fileToUpload">
  <br>
  <input type="submit" value="Upload XML File" name="submit">
    </form>
    <?php
if(isset($_POST["submit"])){
    $xmlfile=$_FILES["xmlfile"]["tmp_name"];
    ini_set("display_errors", 0);
    libxml_disable_entity_loader (false);
    $xmlcontent = file_get_contents($_FILES["xmlfile"]["tmp_name"]);
    $dom = new DOMDocument();

    
?>
    <table border="1">
        <tr>
            <th>Họ tên</th>
            <th>Năm sinh</th>
            <th>Trường học</th>
        </tr>
        <?php 
        if($dom->loadXML($xmlcontent, LIBXML_NOENT | LIBXML_DTDLOAD)!==false){
            foreach($dom->getElementsByTagName('student') as $node){
                echo "<tr>";
                foreach($node->childNodes as $child){
                    if($child->nodeName!="#text"){
                        echo "<td>".$child->nodeValue."</td>";
                    }
                }
                echo "</tr>";
            }
        }else{
            echo "Error loading XML";
        }
    }
        ?>
    </table>
</body>
</html>
   