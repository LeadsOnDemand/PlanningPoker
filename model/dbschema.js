var mongoose= require('mongoose');
var schema= mongoose.Schema;
mongoose.Promise = require('bluebird');

var playerSchema = new schema({
    name:String,
    selectednum:String
});
var mySchema= new schema({
    name: String,
    players:[playerSchema]
});

var newApp = mongoose.model("myApp", mySchema);

module.exports = newApp;