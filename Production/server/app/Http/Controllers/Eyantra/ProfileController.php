<?php

namespace App\Http\Controllers\Eyantra;
use App\Profile;
use Illuminate\Http\Request;
use App\Http\Controllers\Controller;

class ProfileController extends Controller
{
    public function store(Request $request)
	{
		$input= new Profile;
		$input->Profile_Pic=$request->Profile_Pic;
		$input->name=$request->name;
		
		$input->college=$request->college;
		$input->email=$request->email;
		$input->phone=$request->phone;
		$phone=$input->phone;
		$input->job=$request->job;
		$input->company=$request->company;
		$input->year=$request->year;
		$input->branch=$request->branch;
		$input->type=$request->type;
		$input->save();
		$data = new \stdClass();
		$data->Response=Profile::where('phone',"$phone")->value('unique');
		echo json_encode($data);
	}
	public function show(Request $request)
	{
		$input=$request->unique;
		$prof=Profile::where('unique',"$input")->get();
		echo $prof;
	}
}
