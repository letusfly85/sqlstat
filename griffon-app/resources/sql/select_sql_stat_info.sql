select
	 ses.USERNAME
	,ses.STATUS
	,ses.EVENT
	,ops.SQL_ID
	,sql.SQL_TEXT
	,ops.ELAPSED_SECONDS
	,ops.TIME_REMAINING
	,(ops.ELAPSED_SECONDS + ops.TIME_REMAINING) as TOTAL
	,ops.OPNAME
	,ops.TARGET
from
	 V$SESSION_LONGOPS ops
	,V$SESSION         ses
	,V$SQL             sql
where
	1 = 1
and ses.SID      = ops.SID
and ses.SERIAL#  = ops.SERIAL#
and ops.SQL_ID = sql.SQL_ID
and ses.USERNAME is not null
and to_char(ops.START_TIME, 'yyyymmdd') = to_char(sysdate, 'yyyymmdd')
and ops.TIME_REMAINING <> 0
--and sql.SQL_ID = ?