<?php

namespace App\Http\Controllers\Eyantra;
use App\Sign;
use Illuminate\Http\Request;
use App\Http\Controllers\Controller;

class SignController extends Controller
{
    public function store(Request $request)
	{
		$input= new Sign;
		$input->name=$request->name;
		$input->email=$request->email;
		$input->save();
		echo "Saved";
	}
}
