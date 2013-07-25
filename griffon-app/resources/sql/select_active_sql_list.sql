select
	 ses.SQL_ID
	,ses.STATUS
	,ses.USERNAME
	,case ses.COMMAND
		when 3  then 'SELECT'
		when 2  then 'INSERT'
		when 7  then 'DELETE'
		when 6  then 'UPDATE'
		when 12 then 'DROP TABLE'
		when 85 then 'TRUNCATE TABLE'
		else to_char(ses.COMMAND)
	end as command
	,sql.SQL_TEXT
	,sql.SQL_FULLTEXT
from
	 v$sql sql
	,v$session ses
where
	ses.SQL_ID = sql.SQL_ID
and ses.STATUS = 'ACTIVE'