DESCRIPTION = "The NetCDF3 CXX-library"
HOMEPAGE = "http://www.unidata.ucar.edu/software/netcdf/"
SECTION = "libs"
LICENSE = "CLOSED"

DEPENDS = "hdf5 netcdf4"

SRC_URI = "ftp://ftp.unidata.ucar.edu/pub/netcdf/netcdf-cxx-${PV}.tar.gz"

S = "${WORKDIR}/netcdf-cxx-${PV}"

SRC_URI[md5sum] = "d32b20c00f144ae6565d9e98d9f6204c"
SRC_URI[sha256sum] = "95ed6ab49a0ee001255eac4e44aacb5ca4ea96ba850c08337a3e4c9a0872ccd1"

inherit autotools pkgconfig
