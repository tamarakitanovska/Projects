@extends('main')
@section('title', '| Create New Post')
@section('content')
    <br>
    <br>
    <div align="center">
    <h1>Create New Post</h1>
    <hr>
        <br>
    {!! Form::open(['route' => 'posts.store' ]) !!}
    {{ Form::label('title', 'Title:') }}
    {{ Form::text('title',null, array('class' => 'form-control')) }}
<br>
        <br>
        {{ Form::label('name', "Post Body:") }}
        <br>
        {{ Form::textarea('name', null, array('class' => 'form-control')) }}
<br>
        <br>
        {{ Form::submit('Create Post',array('class' => 'btn btn-success btn-lg btn-block')) }}
        <br>
        <br>
        <br>
        <br>
        <br>
    {!! Form::close() !!}
    </div>
    @endsection