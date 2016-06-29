<?php
    // array for JSON response
    $response= array();
    // check for required fields
    if(isset($_POST['patient_id']) && isset($_POST['patient_name']) && isset($_POST['gender']) && isset($_POST['age']) && isset($_POST['height']) &&  isset($_POST['weight'] && isset($_POST['leg_length']) )) {
        $patient_id= intval($_POST['patient_id']);
        $patient_name= $_POST['patient_name'];
        $gender= intval($_POST['gender']);
        $age= intval($_POST['age']);
        $height= floatval($_POST['height']);
        $weight= floatval($_POST['weight']);
        $leg_length= floatval($_POST['leg_length']);
        // include db connect class
        require_once__DIR__ . '/db_connect.php';
        // connecting to db
        $db= new DB_CONNECT();
        // mysql update row with matched patient_id
        $result= mysql_query("UPDATE patient SET name = '$name', gender = '$gender', age = '$age', height = '$height', weight = '$weight', leg_length = '$leg_length' WHERE patient_id = $patient_id");
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