function readURL(input) {
        if (input.files && input.files[0]) {
            var reader = new FileReader();
            reader.onload = function (event) {
                $('#preview').attr('src', event.target.result);
                $('#post-form').attr('action','/post/add/'+event.target.result);
            }
            reader.readAsDataURL(input.files[0]);
        }
    }