<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>

	<head>
		<title>Webapp Banca</title>
	</head>

	<body>
        <center>
            <h1>Apri Conto Cliente</h1>
        </center>
		
		<form action="/banca/apri-conto-cliente" method="post">
		
			<p>Per favore, indicare i dati per il cliente di cui si vuole aprire il conto!!!.</p>
			
			<label for="iban">Iban</label>
			<br>
			<input type="text" id="iban" name="iban" minlength="32" maxlength="32" value="" pattern="^(?:(?:IT|SM)\d{2}[A-Z]\d{10}[0-9A-Z]{12}|CY\d{2}[A-Z]\d{23}|NL\d{2}[A-Z]{4}\d{10}|LV\d{2}[A-Z]{4}\d{13}|(?:BG|BH|GB|IE)\d{2}[A-Z]{4}\d{14}|GI\d{2}[A-Z]{4}\d{15}|RO\d{2}[A-Z]{4}\d{16}|KW\d{2}[A-Z]{4}\d{22}|MT\d{2}[A-Z]{4}\d{23}|NO\d{13}|(?:DK|FI|GL|FO)\d{16}|MK\d{17}|(?:AT|EE|KZ|LU|XK)\d{18}|(?:BA|HR|LI|CH|CR)\d{19}|(?:GE|DE|LT|ME|RS)\d{20}|IL\d{21}|(?:AD|CZ|ES|MD|SA)\d{22}|PT\d{23}|(?:BE|IS)\d{24}|(?:FR|MR|MC)\d{25}|(?:AL|DO|LB|PL)\d{26}|(?:AZ|HU)\d{27}|(?:GR|MU)\d{28})$"  />
	
			<br>	
			
			<label for="valuta">Valuta</label><br>
			<input type="text" id="valuta" name="valuta" minlength="3" maxlength="3" value="" pattern="[A-Z]{3}"  />
	
			<br>

			<label for="codice-fiscale">Codice Fiscale</label><br>
			<input type="text" id="codice-fiscale" name="codice-fiscale" minlength="16" maxlength="16" value="" pattern="^[a-zA-Z]{6}[0-9]{2}[abcdehlmprstABCDEHLMPRST]{1}[0-9]{2}([a-zA-Z]{1}[0-9]{3})[a-zA-Z]{1}$"  />

			<br>
		
			<input type="submit" value="apri conto cliente">
			
		</form>
  
	</body>
</html>