USE [master]
GO
/****** Object:  Database [TrainingsBase]    Script Date: 01.11.2020 22:11:35 ******/
CREATE DATABASE [TrainingsBase]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'TrainingsBase', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL14.MSSQLSERVER\MSSQL\DATA\TrainingsBase.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'TrainingsBase_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL14.MSSQLSERVER\MSSQL\DATA\TrainingsBase_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
GO
ALTER DATABASE [TrainingsBase] SET COMPATIBILITY_LEVEL = 140
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [TrainingsBase].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [TrainingsBase] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [TrainingsBase] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [TrainingsBase] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [TrainingsBase] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [TrainingsBase] SET ARITHABORT OFF 
GO
ALTER DATABASE [TrainingsBase] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [TrainingsBase] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [TrainingsBase] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [TrainingsBase] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [TrainingsBase] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [TrainingsBase] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [TrainingsBase] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [TrainingsBase] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [TrainingsBase] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [TrainingsBase] SET  DISABLE_BROKER 
GO
ALTER DATABASE [TrainingsBase] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [TrainingsBase] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [TrainingsBase] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [TrainingsBase] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [TrainingsBase] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [TrainingsBase] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [TrainingsBase] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [TrainingsBase] SET RECOVERY FULL 
GO
ALTER DATABASE [TrainingsBase] SET  MULTI_USER 
GO
ALTER DATABASE [TrainingsBase] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [TrainingsBase] SET DB_CHAINING OFF 
GO
ALTER DATABASE [TrainingsBase] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [TrainingsBase] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [TrainingsBase] SET DELAYED_DURABILITY = DISABLED 
GO
EXEC sys.sp_db_vardecimal_storage_format N'TrainingsBase', N'ON'
GO
ALTER DATABASE [TrainingsBase] SET QUERY_STORE = OFF
GO
USE [TrainingsBase]
GO
/****** Object:  Table [dbo].[LevelTraining]    Script Date: 01.11.2020 22:11:35 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[LevelTraining](
	[TrainingId] [int] NOT NULL,
	[Lite] [bit] NOT NULL,
	[Medium] [bit] NOT NULL,
	[Hard] [bit] NOT NULL,
	[LevelId] [int] NOT NULL,
 CONSTRAINT [PK_LevelTraining] PRIMARY KEY CLUSTERED 
(
	[LevelId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Settings]    Script Date: 01.11.2020 22:11:35 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Settings](
	[AudioManager] [nchar](10) NULL,
	[NameUser] [nvarchar](200) NOT NULL,
 CONSTRAINT [PK_Settings] PRIMARY KEY CLUSTERED 
(
	[NameUser] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Statistic]    Script Date: 01.11.2020 22:11:35 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Statistic](
	[NameUser] [nvarchar](200) NOT NULL,
	[TrainingId] [int] NULL,
	[TimeTraining] [time](7) NULL,
	[TrainingCount] [nchar](10) NULL,
 CONSTRAINT [PK_Statistic] PRIMARY KEY CLUSTERED 
(
	[NameUser] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[TimeTraining]    Script Date: 01.11.2020 22:11:35 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[TimeTraining](
	[ExerciseInterval] [time](7) NOT NULL,
	[ResultInterval] [time](7) NOT NULL,
	[TotalInterval] [time](7) NOT NULL,
	[TrainingId] [int] NOT NULL,
	[Description] [nvarchar](max) NULL,
 CONSTRAINT [PK_TimeTraining] PRIMARY KEY CLUSTERED 
(
	[TrainingId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Training]    Script Date: 01.11.2020 22:11:35 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Training](
	[Trainingld] [int] NOT NULL,
	[TypeOfTraining] [nvarchar](100) NULL,
	[TimeTraining] [nchar](10) NULL,
	[LevelTraining] [nvarchar](50) NULL,
	[ImagePreview] [varbinary](max) NOT NULL,
	[Price] [money] NOT NULL,
	[IsActual] [bit] NOT NULL,
	[CountOfStars] [int] NOT NULL,
 CONSTRAINT [PK_Training] PRIMARY KEY CLUSTERED 
(
	[Trainingld] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[TrainingComment]    Script Date: 01.11.2020 22:11:35 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[TrainingComment](
	[TrainingId] [int] NOT NULL,
	[Text] [nvarchar](max) NULL,
	[Author] [nvarchar](max) NOT NULL,
	[CreationDate] [datetime] NULL,
	[IdComment] [int] NOT NULL,
 CONSTRAINT [PK_TrainingComment] PRIMARY KEY CLUSTERED 
(
	[IdComment] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[TrainingImage]    Script Date: 01.11.2020 22:11:35 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[TrainingImage](
	[TrainingId] [int] NOT NULL,
	[ImageSource] [varbinary](50) NULL,
 CONSTRAINT [PK_TrainingImage] PRIMARY KEY CLUSTERED 
(
	[TrainingId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Type]    Script Date: 01.11.2020 22:11:35 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Type](
	[TrainingId] [int] IDENTITY(1,1) NOT NULL,
	[Description] [nvarchar](max) NULL,
	[TypeId] [int] NOT NULL,
 CONSTRAINT [PK_Type] PRIMARY KEY CLUSTERED 
(
	[TypeId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[TypeOfTraining]    Script Date: 01.11.2020 22:11:35 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[TypeOfTraining](
	[TrainingId] [int] NOT NULL,
	[TypeId] [int] NOT NULL,
 CONSTRAINT [PK_TypeOfTraining] PRIMARY KEY CLUSTERED 
(
	[TrainingId] ASC,
	[TypeId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[User]    Script Date: 01.11.2020 22:11:35 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[User](
	[NameUser] [nvarchar](200) NOT NULL,
	[Weight] [int] NULL,
	[Height] [int] NULL,
	[Age] [int] NULL,
	[Gender] [nchar](2) NULL,
 CONSTRAINT [PK_User] PRIMARY KEY CLUSTERED 
(
	[NameUser] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
ALTER TABLE [dbo].[LevelTraining]  WITH CHECK ADD  CONSTRAINT [FK_LevelTraining_Training] FOREIGN KEY([LevelId])
REFERENCES [dbo].[Training] ([Trainingld])
GO
ALTER TABLE [dbo].[LevelTraining] CHECK CONSTRAINT [FK_LevelTraining_Training]
GO
ALTER TABLE [dbo].[Settings]  WITH CHECK ADD  CONSTRAINT [FK_Settings_User] FOREIGN KEY([NameUser])
REFERENCES [dbo].[User] ([NameUser])
GO
ALTER TABLE [dbo].[Settings] CHECK CONSTRAINT [FK_Settings_User]
GO
ALTER TABLE [dbo].[Statistic]  WITH CHECK ADD  CONSTRAINT [FK_Statistic_User] FOREIGN KEY([NameUser])
REFERENCES [dbo].[User] ([NameUser])
GO
ALTER TABLE [dbo].[Statistic] CHECK CONSTRAINT [FK_Statistic_User]
GO
ALTER TABLE [dbo].[TimeTraining]  WITH CHECK ADD  CONSTRAINT [FK_TimeTraining_Training] FOREIGN KEY([TrainingId])
REFERENCES [dbo].[Training] ([Trainingld])
GO
ALTER TABLE [dbo].[TimeTraining] CHECK CONSTRAINT [FK_TimeTraining_Training]
GO
ALTER TABLE [dbo].[TrainingComment]  WITH CHECK ADD  CONSTRAINT [FK_TrainingComment_Training] FOREIGN KEY([IdComment])
REFERENCES [dbo].[Training] ([Trainingld])
GO
ALTER TABLE [dbo].[TrainingComment] CHECK CONSTRAINT [FK_TrainingComment_Training]
GO
ALTER TABLE [dbo].[TrainingImage]  WITH CHECK ADD  CONSTRAINT [FK_TrainingImage_Training] FOREIGN KEY([TrainingId])
REFERENCES [dbo].[Training] ([Trainingld])
GO
ALTER TABLE [dbo].[TrainingImage] CHECK CONSTRAINT [FK_TrainingImage_Training]
GO
ALTER TABLE [dbo].[TypeOfTraining]  WITH CHECK ADD  CONSTRAINT [FK_TypeOfTraining_Training] FOREIGN KEY([TrainingId])
REFERENCES [dbo].[Training] ([Trainingld])
GO
ALTER TABLE [dbo].[TypeOfTraining] CHECK CONSTRAINT [FK_TypeOfTraining_Training]
GO
ALTER TABLE [dbo].[TypeOfTraining]  WITH CHECK ADD  CONSTRAINT [FK_TypeOfTraining_Type] FOREIGN KEY([TypeId])
REFERENCES [dbo].[Type] ([TypeId])
GO
ALTER TABLE [dbo].[TypeOfTraining] CHECK CONSTRAINT [FK_TypeOfTraining_Type]
GO
USE [master]
GO
ALTER DATABASE [TrainingsBase] SET  READ_WRITE 
GO
