<?php

namespace App\Http\Controllers\Eyantra;
use App\Post;
use Illuminate\Support\Facades\Input;
use Illuminate\Http\Request;
use App\Http\Controllers\Controller;

class PostController extends Controller
{
    public function store(Request $request)
	{
		$response = new \stdClass();
		$input= new Post;
		$input->ID=$request->ID;
		$input->username=$request->username;
		$input->Post=$request->Post;
		$input->photo=$request->photo;
		$input->save();
		$response->Response="Saved";
		echo json_encode($response);
	}
	public function show()
	{
		$data=Post::orderBy('ID','DESC')->get();
		echo json_encode($data);
	}
	public function image(Request $request)
	{	/*$image=new \stdClass();
		$image->response=$request->image;
		echo json_encode($image);*/
	
	$destinationPath=public_path('uploads');
		$file=Input::file('photo');
		Input::file('photo')->move($destinationPath);
		echo "uploaded";
	}
}
