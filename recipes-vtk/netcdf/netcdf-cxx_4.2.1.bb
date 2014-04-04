DESCRIPTION = "The NetCDF4 CXX-library"
HOMEPAGE = "http://www.unidata.ucar.edu/software/netcdf/"
SECTION = "libs"
LICENSE = "CLOSED"

DEPENDS = "hdf5 netcdf"

SRC_URI = "https://github.com/Unidata/netcdf-cxx4/archive/v${PV}.tar.gz"

S = "${WORKDIR}/${PN}4-4.2.1"

SRC_URI[md5sum] = "3bc361ef3196163ae1006c32fdf5b35c"
SRC_URI[sha256sum] = "bad56abfc99f321829070c04aebb377fc8942a4d09e5a3c88ad2b6547ed50ebc"

inherit autotools pkgconfig
