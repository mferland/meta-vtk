DESCRIPTION = "The NetCDF4 CXX-library"
HOMEPAGE = "http://www.unidata.ucar.edu/software/netcdf/"
SECTION = "libs"
LICENSE = "license_netcdf-cxx"
LIC_FILES_CHKSUM = "file://COPYRIGHT;md5=7a265567ba44537b8d1ed8b406d4b25c"

DEPENDS = "hdf5 netcdf4"

SRC_URI = "https://github.com/Unidata/netcdf-cxx4/archive/v${PV}.tar.gz"

S = "${WORKDIR}/netcdf-cxx4-${PV}"

SRC_URI[md5sum] = "3bc361ef3196163ae1006c32fdf5b35c"
SRC_URI[sha256sum] = "bad56abfc99f321829070c04aebb377fc8942a4d09e5a3c88ad2b6547ed50ebc"

inherit autotools pkgconfig
