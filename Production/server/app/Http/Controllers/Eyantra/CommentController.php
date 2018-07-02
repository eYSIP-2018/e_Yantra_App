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
		$input->save();
		echo "Saved";
	}
	public function show(Request $request)
	{
		$input=$request->pid;
		$data = Comment::where('Pid',"$input")->get();
		echo $data;
		
	}
}
