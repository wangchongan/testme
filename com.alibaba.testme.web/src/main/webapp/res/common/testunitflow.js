function moveItem(source, target) {
    var choiceOptions = source.options;
    var selectedOptions = target.options;
	if(choiceOptions.length){
    	for (var i = 0; i < (choiceOptions.length); i++) {
            var temp = choiceOptions.item(i);
            if (temp.selected == true) {
                //var oOption = document.createElement("OPTION");
                selectedOptions[selectedOptions.length] = new Option(temp.text, temp.value);
				choiceOptions[i] = null;
            }
        }
	}
}

function adjustUp(source) {
    var itemOptions = source.options;
    var selectedOption;
    var count = 0;
    var index=0;
	if(itemOptions.length){
    	for (var i = 0; i < itemOptions.length; i++) {
            var temp = itemOptions.item(i);
            if (temp.selected == true) {
                count++;
                if (count > 1) {
                    alert("只能选择一个列调整顺序！");
                    return;
                } else if (count == 1) {
                    index = i;
                }
            }
        }
	}
    
    if (count == 0) {
        alert("请先选择需要调整顺序的列！");
        return;
    }

    if (index == 0) return;

    selectedOption = itemOptions[index];
    var lastOption = itemOptions[index - 1];
    var temp = new Option(selectedOption.text, selectedOption.value);

    selectedOption.text = lastOption.text;
    selectedOption.value = lastOption.value;
    selectedOption.selected = false;

    lastOption.text = temp.text;
    lastOption.value = temp.value;
    lastOption.selected = true;
}

function adjustDown(source) {
    var itemOptions = source.options;
    var selectedOption;
    var count = 0;
    var index=0;
	if(itemOptions.length){
        for (var i = 0; i < itemOptions.length; i++) {
            var temp = itemOptions[i];
            if (temp.selected == true) {
                count++;
                if (count > 1) {
                    alert("只能选择一个列调整顺序！");
                    return;
                } else if (count == 1) {
                    index = i;
                }
            }
        }
	}
    if (count == 0) {
        alert("请选择要调整顺序的列！");
        return;
    }

    if (index == itemOptions.length - 1) return;
    selectedOption = itemOptions[index];
    var nextOption = itemOptions[index + 1];
    var temp = new Option(selectedOption.text, selectedOption.value);

    selectedOption.text = nextOption.text;
    selectedOption.value = nextOption.value;
    selectedOption.selected = false;

    nextOption.text = temp.text;
    nextOption.value = temp.value;
    nextOption.selected = true;
}

function submitForm(formId,actionName,source){
	var itemOptions = source.options;
	var valueStr="";
	if(itemOptions.length){
        for (var i = 0; i < itemOptions.length; i++) {
            var temp = itemOptions[i];
            valueStr+=(temp.value+"#");
        }
	}
	document.getElementById("testunitIdStr").value=valueStr; 
	submitFormProcess(formId,actionName);
}