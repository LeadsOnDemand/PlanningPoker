var ex = require('express');
var app=ex();
var mongoose = require('mongoose');
var port= process.env.PORT || 80;
var apiController = require('./controller/apiController');
app.use('/', ex.static(__dirname + './public'));
apiController(app);
mongoose.connect('mongodb://localhost:27017/myApp', { useMongoClient:true});
app.listen(port);