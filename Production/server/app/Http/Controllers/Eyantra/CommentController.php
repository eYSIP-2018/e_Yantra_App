<?php

namespace App\Http\Controllers\Eyantra;
use App\Comment;
use Illuminate\Http\Request;
use App\Http\Controllers\Controller;

class CommentController extends Controller
{
	public function store(Request $request)
	{
    $input= new Comment;
		$input->username=$request->username;
		$input->Comments=$request->Comments;
		$input->Pid=$request->Pid;
		$input->photo=$request->photo;
		$input->save();
		echo "Saved";
	}
	public function show(Request $request)
	{
		
		$input=$request->pid;
		
		$data = Comment::where('Pid',"$input")
		->orderBy('ID','DESC')->get();
		echo $data;
		
		
	}
	public function count(Request $request)
	{
		$input=$request->pid;
		$count =Comment::where('Pid',"$input")
		->count();
		echo $count;
	}
}
