<?php

namespace App\Http\Controllers;
class PagesController extends Controller{
    public function getIndex(){
        return view('pages.home');
    }
    public function getAbout(){
        $first = "Tamara";
        $last = "Kitanovska";
        $full = $first . " " . $last;
        $email = "kitanovska.tamara@yahoo.com";
        return view('pages.about')->with("fullname",$full)->with("email", $email);
    }
    public function getContact(){
        return view('pages.contact');
    }
    public function postContact(){

    }
    public function getAddress(){
        return view('pages.zad1');
    }
}

?>