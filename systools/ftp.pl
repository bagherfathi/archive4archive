#!/usr/bin/perl
use strict;
use Net::FTP;
use Archive::Zip;

my $host = "192.168.1.68";
my $user = "tingwei";
my $password = "weiwei";
my $sourcedirectory = "D:\\perltest\\source";
my $directory = "target";
my $archive = "D:\\perltest\\archive";
my $second;my $minute;my $hour;my $month;my $yearOffset;my $dayOfMonth;my $dayOfWeek;my $dayOfYear;my $daylightSavings;
($second, $minute, $hour, $dayOfMonth, $month, $yearOffset, $dayOfWeek, $dayOfYear, $daylightSavings) = localtime();
my $year = 1900 + $yearOffset;#  start from 1900
$month = $month + 1; # start from 0
$month = sprintf("%02d",$month);
$dayOfMonth = sprintf("%02d",$dayOfMonth);
my $date = $year.$month.$dayOfMonth.$hour.$minute.$second;
my $arcfilename = "archive_".$date.".zip";
my $recipient = "karl";

unless (-d $archive)
{
    mkdir $archive or die "could not create $archive directory!\n";
}
chdir $sourcedirectory;
my @files = <*.*>;
if (@files == 0)
{
    print "Exiting, nothing to process!\n";
    exit(0);
}

my $ftp = Net::FTP->new($host, Timeout=>200) or die "failed to connect to $host!\n";
$ftp->login($user, $password) or die "failed to authenticate user $user!\n";
$ftp->binary or die "failed to set binary transfer mode!\n";
#$ftp->
#$ftp->mkdir($directory) or die "failed to create $directory";
$ftp->cwd($directory) or die "failed to set working directory $directory!\n";


my $zip = Archive::Zip->new();
foreach my $file (@files)
{
    $zip->addFile($file);
}
#$zip->addDirectory($sourcedirectory);
$zip->writeToFileNamed($archive."\\".$arcfilename);

#my $uploadfilename=sprintf("%s\%s",);

$ftp->put($archive."\\".$arcfilename) or die "$archive\$arcfilename failed to upload file ";
print $ftp->message;
$ftp->quit();

foreach my $file (@files)
{
    unlink $file;
}

exit 0;