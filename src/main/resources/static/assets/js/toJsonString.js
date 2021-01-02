function toJsonString(form) {

    var obj = {};
    var elements = form.find("input:not([type='hidden']):not([type='file']):not('.hiddenForSpecs')");

    for(var i=0; i<elements.length; i++) {

        var element = elements[i];
        var name = element.name;
        var value = element.value;

        if(name) obj[name] = value;
    }

    return JSON.stringify(obj);
}