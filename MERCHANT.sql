/****** Script for SelectTopNRows command from SSMS  ******/
SELECT TOP (1000) [id]
      ,[address]
      ,[age]
      ,[bank_acc]
      ,[bus_type]
      ,[company_name]
      ,[email]
      ,[fone_no]
      ,[password]
      ,[salary]
      ,[username]
   from  [ExoSkeleton].[dbo].[tbl_mer_users]

  ----where id =2