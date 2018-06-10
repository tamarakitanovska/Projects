@extends('main')
@section('title', '| Edit Blog Post')
@section('content')
    {!! Form::model($post, ['route' => ['posts.update', $post->id], 'method' => 'PUT']) !!}
    <p>
        This is the blog post
    </p>
    {{Form::text('title', null, ["class" => 'form-control'])}}
    {{Form::textarea('name',null, ['class' =>'form-control '])}}
    <br>
    <br>
    <p>Create At: {{date('M j, Y h:ia', strtotime($post->created_at))}}</p>
    <p>Last Updated: {{date('M j, Y h:ia',strtotime($post -> updated_at))}}</p>
    <br>
    {!! Html::linkRoute('posts.show', 'Cancel', array($post->id), array('class' =>'btn btn-primary btn-block btn-danger')) !!}
    {{Form::submit('Save Changes', ['class' => 'btn btn-primary btn-block btn-success'])}}
{!! Form::close() !!}
@endsection