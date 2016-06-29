<?php
    // array for JSON response
    $response= array();
    // check for required fields
    if(isset($_POST['patient_id']) && isset($_POST['age']) && isset($_POST['height']) && isset($_POST['gender']) && isset($_POST['weight'])) {
        $patient_id= intval($_POST['patient_id']);
        $age= intval($_POST['age']);
        $height= floatval($_POST['height']);
        $gender= intval($_POST['gender']);
        $weight= floatval($_POST['weight']);
        // include db connect class
        require_once__DIR__ . '/db_connect.php';
        // connecting to db
        $db= new DB_CONNECT();
        // mysql update row with matched patient_id
        $result= mysql_query("UPDATE patient SET age = $age, height = $height, gender = $gender, weight = $weight WHERE patient_id = $patient_id");
        // check if row inserted or not
        if($result) {
            // successfully updated
            $response["success"] = 1;
            $response["message"] = "Product successfully updated.";
            // echoing JSON response
            echo json_encode($response);
        } else{
        } 
    } else{ 
        // required field is missing 
        $response["success"] = 0; 
        $response["message"] = "Required field(s) is missing"; 
        // echoing JSON response 
        echo json_encode($response);
    } 
    ?>