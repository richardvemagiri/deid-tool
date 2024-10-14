# DeID Tool

DeID Tool is a Java-based application with a web UI that allows users to de-identify C-CDA XML documents by replacing personally identifiable information (PII) with static text. This helps anonymize healthcare data in compliance with HIPAA.

## Key Features
- **C-CDA XML De-identification**: Automatically detects and replaces PII in clinical document architecture (C-CDA) XML files.
- **Multiple Input Options**: Users can either upload a C-CDA XML file or enter XML text manually.
- **PII Masking**: Sensitive data is replaced with static placeholders, ensuring full anonymization.
- **De-identified Output**: The de-identified C-CDA XML can be downloaded once the process is complete.
- **Database Integration**: Uses a relational database to store configurations.