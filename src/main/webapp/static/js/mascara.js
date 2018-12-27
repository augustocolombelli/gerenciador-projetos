function mascaraCPF(cpf) {
	var cpfValue = cpf.value;
	if (isNaN(cpfValue[cpfValue.length - 1])) {
		cpf.value = cpfValue.substring(0, cpfValue.length - 1);
		return;
	}
	cpf.setAttribute("maxlength", "14");
	if (cpfValue.length == 3 || cpfValue.length == 7)
		cpf.value += ".";
	if (cpfValue.length == 11)
		cpf.value += "-";
}