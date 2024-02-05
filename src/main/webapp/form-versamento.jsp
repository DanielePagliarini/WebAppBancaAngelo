<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>

	<head>
		<title>Webapp Banca</title>
	</head>

	<body>
        <center>
            <h1>Versamento Cliente</h1>
        </center>
		
		<form action="/WebAppBanca/form-versamento" method="post">
		
			<p>Per favore, indicare i dati corretti per il versamento!!!.</p>
			
			<label for="iban">Iban</label>
			<br>
			<input type="text" id="iban" name="iban" minlength="32" maxlength="32" value="" pattern="^(?:(?:IT|SM)\d{2}[A-Z]\d{10}[0-9A-Z]{12}|CY\d{2}[A-Z]\d{23}|NL\d{2}[A-Z]{4}\d{10}|LV\d{2}[A-Z]{4}\d{13}|(?:BG|BH|GB|IE)\d{2}[A-Z]{4}\d{14}|GI\d{2}[A-Z]{4}\d{15}|RO\d{2}[A-Z]{4}\d{16}|KW\d{2}[A-Z]{4}\d{22}|MT\d{2}[A-Z]{4}\d{23}|NO\d{13}|(?:DK|FI|GL|FO)\d{16}|MK\d{17}|(?:AT|EE|KZ|LU|XK)\d{18}|(?:BA|HR|LI|CH|CR)\d{19}|(?:GE|DE|LT|ME|RS)\d{20}|IL\d{21}|(?:AD|CZ|ES|MD|SA)\d{22}|PT\d{23}|(?:BE|IS)\d{24}|(?:FR|MR|MC)\d{25}|(?:AL|DO|LB|PL)\d{26}|(?:AZ|HU)\d{27}|(?:GR|MU)\d{28})$"  />
	
			<br>	
			
			<label for="valuta">Importo</label><br>
			<input type="text" id="importo" name="importo" min="0" />
	
			<br>

		
			<input type="submit" value="Versamento">
			
		</form>
  
	</body>
</html>