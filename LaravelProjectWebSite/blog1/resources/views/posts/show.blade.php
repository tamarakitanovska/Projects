@extends('main')
    @section('title', '| View Post')
@section('content')
    <p>
        This is the blog post
    </p>
    <p>Title: {{$post->title}}</p>
    <br>
    <br>
    <p>Create At: {{date('M j, Y h:ia', strtotime($post->created_at))}}</p>
    <p>Last Updated: {{date('M j, Y h:ia',strtotime($post -> updated_at))}}</p>
    <br>
    {!! Html::linkRoute('posts.edit', 'Edit', array($post->id), array('class' =>'btn btn-primary btn-md btn-default')) !!}

    {!! Form::open(['route'=>['posts.destroy',$post->id], 'method' => 'DELETE' ]) !!}
    <br>
    {!! Form::submit('Delete', ['class' => 'btn btn-danger']    ) !!}
    {!! Form::close() !!}
@endsection